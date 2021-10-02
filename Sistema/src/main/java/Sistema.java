import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Sistema {

    public static void main(String[] args) throws IOException {
        InstagramGraffitiDetector instagramGraffitiDetector = new InstagramGraffitiDetector() ;
        instagramGraffitiDetector.detector();

//        tratamentoPorcentagem2("[[0.08709642]\n" +
//                " [0.08789983]\n" +
//                " [0.08260518]\n" +
//                " [0.09327984]\n" +
//                " [0.08555993]\n" +
//                " [0.0889115 ]]");

    }

//    public static void tratamentoPorcentagem2(String porcentagens){
//
//        List<Double> intList = new ArrayList<>();
//
//        String s1= porcentagens;
//        String replace = s1.replace("[","");
//        System.out.println(replace);
//        String replace1 = replace.replace("]","");
//        System.out.println(replace1);
//        String replace2 = replace1.replace("\n",",");
//        replace2 = replace2.replace(" ","");
//        System.out.println(replace2);
//        List<String> myList = new ArrayList<String>(Arrays.asList(replace2.split(",")));
//        System.out.println(myList.toString());
//        intList.addAll(myList.stream().map(Double::valueOf).collect(Collectors.toList()));
//        System.out.println(intList.toString());
//        Collections.sort(intList, Collections.reverseOrder());
//        System.out.println(intList.toString());
//    }
 }
