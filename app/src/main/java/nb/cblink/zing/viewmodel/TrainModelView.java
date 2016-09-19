package nb.cblink.zing.viewmodel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import nb.cblink.zing.R;
import nb.cblink.zing.data.TrainData;
import nb.cblink.zing.view.activity.MainActivity;

import static nb.cblink.zing.data.TrainData.*;

/**
 * Created by nguyenbinh on 17/09/2016.
 */

public class TrainModelView {

    private Context context;
    private LayoutInflater inflater;

    public TrainModelView(Context context, LayoutInflater inflater) {
        this.context = context;
        this.inflater = inflater;
    }

    public void trainAppZingMP3() {
        showDialogNotConnectInternet("Tải nội dung từ app ZingMP3", 0);
    }

    public void trainWebZingMP3() {
        showDialogNotConnectInternet("Tải nội dung từ web ZingMP3", 1);
    }

    public void trainAppZingTV() {
        showDialogNotConnectInternet("Tải nội dung từ app ZingTV", 2);
    }

    public void trainWebZingTV() {
        showDialogNotConnectInternet("Tải nội dung từ web ZingTV", 3);
    }

    static TrainData trainData = null;

    void showDialogNotConnectInternet(String title, int id) {
        trainData = new TrainData(id);

        View alertLayout = inflater.inflate(R.layout.custom_dialog_train_layout, null);
        Button back = (Button) alertLayout.findViewById(R.id.btn_done);
        final Button next = (Button) alertLayout.findViewById(R.id.btn_next);
        final ImageView imageTrain = (ImageView) alertLayout.findViewById(R.id.imageTrain);
        imageTrain.setImageBitmap(loadSampledResource(trainData.imageData[id][0], 300, 300));

        AlertDialog.Builder builder = new
                AlertDialog.Builder(context);
        builder.setTitle("Bước 1")
                .setView(alertLayout);

        final AlertDialog dialog = builder.create();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idNext = trainData.getNext();
                dialog.setTitle("Bước " + trainData.getStep());
                if (trainData.getIndex() == TrainData.FINISH) {
                    next.setText("Chưa hiểu");
                } else {
                    next.setText("Tiếp theo");
                }
                if (idNext != TrainData.OPEN_WEB) {
                    imageTrain.setImageBitmap(loadSampledResource(idNext, 300, 300));
                } else {
                    dialog.dismiss();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(trainData.urlTrain()));
                    context.startActivity(browserIntent);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idNext = trainData.getBack();
                dialog.setTitle("Bước " + trainData.getStep());
                if (idNext != TrainData.DISMISS) {
                    imageTrain.setImageBitmap(loadSampledResource(idNext, 300, 300));
                } else {
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public Bitmap loadSampledResource(int imageID, int targetHeight,
                                      int targetWidth) {
        final BitmapFactory.Options options = new
                BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), imageID,
                options);
        final int originalHeight = options.outHeight;
        final int originalWidth = options.outWidth;
        int inSampleSize = 1;
        while ((originalHeight / (inSampleSize * 2)) >
                targetHeight && (originalWidth / (inSampleSize * 2))
                > targetWidth) {
            inSampleSize *= 2;
        }
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        return (BitmapFactory.decodeResource(context.getResources(),
                imageID, options));
    }

}
