package nb.cblink.zing.data;

import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import nb.cblink.zing.R;
import nb.cblink.zing.model.AlbumInfo;
import nb.cblink.zing.model.AlbumSong;
import nb.cblink.zing.model.Song;
import nb.cblink.zing.model.ZingMp3Video;
import nb.cblink.zing.model.ZingTVVideo;
import nb.cblink.zing.view.activity.MainActivity;

/**
 * Created by nguyenbinh on 15/09/2016.
 */

public class DownloadResourceBroadcastReceiver extends BroadcastReceiver {


    public DownloadResourceBroadcastReceiver() {
    }

    public void startDownload(Object data, AlbumInfo albumInfo, String type, Context context) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        try {
            if (data instanceof Song) {
                processDownloadSong(type, (Song) data, downloadManager);
            } else if (data instanceof AlbumSong) {
                processDownloadAlbum(type, (AlbumSong) data, albumInfo, downloadManager);
            } else if (data instanceof AlbumSong.Doc) {
                processDownloadSong(type, (AlbumSong.Doc) data, downloadManager);
            }else if (data instanceof ZingMp3Video) {
                processDownloadVideo(type, (ZingMp3Video) data, downloadManager);
            }else if (data instanceof ZingTVVideo) {
                processDownloadVideo(type, (ZingTVVideo) data, downloadManager);
            }
            Toast.makeText(context, "Bắt đầu tải", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            showDialogCanntWrite(context);
        }
    }

    void showDialogCanntWrite(Context context) {
        AlertDialog.Builder builder = new
                AlertDialog.Builder(context);
        builder.setTitle("Không thể lưu dữ liệu")
                .setMessage("Nguyên nhân có thể do:\n" +
                        "\t- Ứng dụng bị tắt quyền ghi dữ liệu\n" +
                        "\t- Không đủ bộ nhớ")
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                            }
                        });
        builder.create().show();
    }

    private void processDownloadSong(String type, Song data, DownloadManager downloadManager) throws Exception {
        Uri uri;
        if (type.equals("128")) {
            uri = Uri.parse(!data.getSource().get128().equals("") ? data.getSource().get128() : data.getSource().get320());
        } else {
            uri = Uri.parse(!data.getSource().get320().equals("") ? data.getSource().get320() : data.getSource().get128());
        }

        DownloadManager.Request request = new DownloadManager.Request(uri);
        //Setting title of request
        request.setTitle(data.getTitle());
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, data.getTitle() + ".mp3");
        downloadManager.enqueue(request);
    }

    private void processDownloadVideo(String type, ZingMp3Video data, DownloadManager downloadManager) throws Exception {
        Uri uri = Uri.parse(type);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        //Setting title of request
        request.setTitle(data.getTitle());
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, data.getTitle() + ".mp4");
        downloadManager.enqueue(request);
    }

    private void processDownloadVideo(String type, ZingTVVideo data, DownloadManager downloadManager) throws Exception {
        Uri uri = Uri.parse(type);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        //Setting title of request
        request.setTitle(data.getResponse().getFullName());
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, data.getResponse().getFullName() + ".mp4");
        downloadManager.enqueue(request);
    }

    private void processDownloadSong(String type, AlbumSong.Doc data, DownloadManager downloadManager) throws Exception {
        Uri uri;
        if (type.equals("128")) {
            uri = Uri.parse(!data.getSource().get128().equals("") ? data.getSource().get128() : data.getSource().get320());
        } else {
            uri = Uri.parse(!data.getSource().get320().equals("") ? data.getSource().get320() : data.getSource().get128());
        }

        DownloadManager.Request request = new DownloadManager.Request(uri);
        //Setting title of request
        request.setTitle(data.getTitle());
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, data.getTitle() + ".mp3");
        downloadManager.enqueue(request);
    }

    private void processDownloadAlbum(String type, AlbumSong data, AlbumInfo albumInfo, DownloadManager downloadManager) throws Exception {
        for (int i = 0; i < data.getDocs().size(); i++) {
            Uri uri;
            if (type.equals("128")) {
                uri = Uri.parse(!data.getDocs().get(i).getSource().get128().equals("") ? data.getDocs().get(i).getSource().get128() : data.getDocs().get(i).getSource().get320());
            } else {
                uri = Uri.parse(!data.getDocs().get(i).getSource().get320().equals("") ? data.getDocs().get(i).getSource().get320() : data.getDocs().get(i).getSource().get128());
            }
            DownloadManager.Request request = new DownloadManager.Request(uri);
            //Setting title of request
            if (i == 0) request.setTitle(albumInfo.getTitle());
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, albumInfo.getTitle() + "/" + data.getDocs().get(i).getTitle() + ".mp3");
            downloadManager.enqueue(request);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

            Intent intentOpenFile = new Intent(Intent.ACTION_VIEW);
            intentOpenFile.setDataAndType(downloadManager.getUriForDownloadedFile(referenceId), "*/*");
            Intent.createChooser(intentOpenFile, "Chọn ứng dụng chơi nhạc");

            DownloadManager.Query myDownloadQuery = new DownloadManager.Query();
            //set the query filter to our previously Enqueued download
            myDownloadQuery.setFilterById(referenceId);

            //Query the download manager about downloads that have been requested.
            Cursor cursor = downloadManager.query(myDownloadQuery);
            cursor.moveToFirst();
            int filenameIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
            String fileName = "";
            String location = "";
            try {
                fileName = cursor.getString(filenameIndex);
                filenameIndex = fileName.lastIndexOf("/");
                location = fileName.substring(0, fileName.lastIndexOf("/"));
                fileName = fileName.substring(filenameIndex + 1, fileName.length());
            }catch (NullPointerException e){
                fileName = "Tải xuống hoàn tất";
                location = "Thư mục lưu: Download";
            }
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentOpenFile, PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.Builder notificationBuilder = new
                    NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.play)
                    .setContentTitle(fileName)
                    .setContentText("Thư mục lưu: \n" + location)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(
                            Context.NOTIFICATION_SERVICE);
            notificationManager.notify(19870,
                    notificationBuilder.build());
        }
    }
}
