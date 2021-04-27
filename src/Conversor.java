import commons.RGB;
import commons.YIQ;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import static org.opencv.core.CvType.CV_32S;

public class Conversor {

    public static void main(String[] args){
        System.out.println("Conversor RGB - YIQ:\n");

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat img = Imgcodecs.imread("./img/colo.jpg",1);
        img.convertTo(img,CV_32S);


        for(int i=0; i < img.height(); i++){
            for(int j= 0 ; j< img.width(); j++){
                double[] doubles = img.get(i,j);
                RGB rgb = new RGB(doubles);
                YIQ yiq = rgb.convertToYiq();
                img.put(i,j,yiq.getInArray());
                double[] x = img.get(i,j);
            }
        }

        Imgcodecs.imwrite("img/result/yiq.jpg", img);
        System.out.println("Conversor YIQ - RGB:\n");

        Mat img2 = img.clone();
        img2.convertTo(img,CV_32S);

        for(int i=0; i < img2.height(); i++){
            for(int j= 0 ; j< img2.width(); j++){
                double[] doubles = img2.get(i,j);
                YIQ yiq = new YIQ(doubles);
                RGB rgb = yiq.convertToRgb();
                System.out.println("Pixels: "+yiq.getY()+" "+yiq.getI()+" "+yiq.getQ());
                img2.put(i,j,rgb.getArray());
                double[] x = img2.get(i,j);
                System.out.println("Pixel salvo: "+x[0]+" "+x[1]+" "+x[2] );
            }
        }

        Imgcodecs.imwrite("img/result/rgb.jpg", img2);

    }

}
