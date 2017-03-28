# ProgressView
A library used to draw the ProgressView that indicates the current progress level in various stages.

# Overview
ProgressView is a view that can be used to draw the current progress level indication of any ongoing process by stages.

# Usage
Create a ProgressView by using its xml tag described below:-

<com.raj.progressview.ProgressView
        android:layout_width="match_parent"
        android:layout_height="20dp"
        app:circleRadius="8dp"
        app:count="5"
        app:lineDepth="5dp"
        app:progress="25"
        app:stage="2" />
     
# Attributes Descriptions
  circleRadius - This defines the dimension value for radius that how big you want to draw the circle component in the progress view.
  
  lineDepth - This defines the dimension value for the line depth in progress view.
  
  count - This attribute tells how many stages available for your process to be completed.
  
  progress - This refers the current progress level of the current stage.
  
  stage - This tells the current stage of the progress.
  
# Basic Sample
  ![Demo Screenshot](https://github.com/nrk1989/ProgressView/blob/master/ScreenShots/ProgressView_Demo.png)
