// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.shappy.shappy.imagelabeling;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionLabelDetector;
import com.shappy.shappy.FrameMetadata;
import com.shappy.shappy.GraphicOverlay;
import com.shappy.shappy.ResultActivity;
import com.shappy.shappy.VisionProcessorBase;

import java.io.IOException;
import java.util.List;

/** Custom Image Classifier Demo. */
public class ImageLabelingProcessor extends VisionProcessorBase<List<FirebaseVisionLabel>> {

  private static final String TAG = "ImageLabelingProcessor";

  private final FirebaseVisionLabelDetector detector;

  private Context context;

  private static boolean clicked;

  public ImageLabelingProcessor(Context context) {
    detector = FirebaseVision.getInstance().getVisionLabelDetector();
    this.context = context;
  }

  public static void setClicked() {
      clicked = true;
  }

  @Override
  public void stop() {
    try {
      detector.close();
    } catch (IOException e) {
      Log.e(TAG, "Exception thrown while trying to close Text Detector: " + e);
    }
  }

  @Override
  protected Task<List<FirebaseVisionLabel>> detectInImage(FirebaseVisionImage image) {
    return detector.detectInImage(image);
  }

  @Override
  protected void onSuccess(
      @NonNull List<FirebaseVisionLabel> labels,
      @NonNull FrameMetadata frameMetadata,
      @NonNull GraphicOverlay graphicOverlay) {
    graphicOverlay.clear();
    LabelGraphic labelGraphic = new LabelGraphic(graphicOverlay, labels);
    graphicOverlay.add(labelGraphic);
    boolean isShoe = labels.stream().anyMatch(t -> t.getLabel().equals("Shoe"));
    if (isShoe && clicked) {
        stop();
        Intent myIntent = new Intent(context, ResultActivity.class);
        context.startActivity(myIntent);
    }
  }

  @Override
  protected void onFailure(@NonNull Exception e) {
    Log.w(TAG, "Label detection failed." + e);
  }
}

