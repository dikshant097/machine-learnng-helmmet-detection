import org.opencv.core.Core; 
import org.opencv.core.Mat; 
import org.opencv.core.MatOfRect; 
import org.opencv.core.Point; 
import org.opencv.core.Rect; 
import org.opencv.core.Scalar; 
import org.opencv.imgcodecs.Imgcodecs; 
import org.opencv.imgproc.Imgproc; 
import org.opencv.objdetect.CascadeClassifier; 
  
 class HelmetDetection 
{ 
    public static void main(String[] args) 
    {  
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  
        CascadeClassifier helmetDetector = new CascadeClassifier(); 
        helmetDetector.load("helmet.xml"); 
  
        // Input image 
        Mat image = Imgcodecs.imread("E:\\Major\\final\\input\\2.jpg"); 
  
        // Detecting Helemt 
        MatOfRect helmet = new MatOfRect(); 
        helmetDetector.detectMultiScale(image, helmet); 
  
        // Creating a rectangular box showing faces detected 
		
		if(helmet.toArray().length!=0)
		{
			 
				
			CascadeClassifier numberPlate = new CascadeClassifier(); 
			numberPlate.load("numberplate.xml");

			
			MatOfRect number=new MatOfRect();
			numberPlate.detectMultiScale(image, number);
			
			
			 for (Rect rect : number.toArray()) 
			{
				System.out.println("number plate");
				Imgproc.rectangle(image, new Point(rect.x, rect.y), 
				new Point(rect.x + rect.width, rect.y + rect.height), 
                                          new Scalar(0, 255, 0));
			}
			for (Rect rect : helmet.toArray()) 
			{ 
				System.out.println("No Helmet");
				Imgproc.rectangle(image, new Point(rect.x, rect.y), 
				new Point(rect.x + rect.width, rect.y + rect.height), 
                                           new Scalar(0, 255, 0)); 
			}
			String filename="output.jpg";	
			Imgcodecs.imwrite("E:\\Major\\final\\output\\"+filename, image); 
			
		} 
        
    } 
} 