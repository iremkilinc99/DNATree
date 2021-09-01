import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

public class DNADatabase {
    public static void main(String args[]) throws IOException {
       DNATree dnaTree = new DNATree();
       BufferedReader directiveFile = null;

       try {
          directiveFile = new BufferedReader(new FileReader(String.valueOf(FileSystems.getDefault().getPath(args[0]).toAbsolutePath()))); //"src/directivesFile"
       } catch (FileNotFoundException e) {
          e.printStackTrace();
       }

       String method = "";
       String parameter = "";
       String[] directive;

       while (directiveFile.ready()) {
             method = directiveFile.readLine();
             directive = method.split(" ");
             method = directive[0];
             if(directive.length > 1)
                 parameter = directive[1];

             if(method.equals("insert"))
                dnaTree.insert(parameter);

             else if(method.equals("display"))
                dnaTree.display(0); //flag 0 ise sequence length yazdırmaz

             else if(method.equals("display-lengths"))
                dnaTree.display(1);

             else if (method.equals("remove"))
                dnaTree.remove(parameter);

             else if (method.equals("search")){
                   if(parameter.contains("$"))
                      dnaTree.search(parameter, 0); // flag 0 ise sequence icin exact math arar
                   else
                      dnaTree.search(parameter, 1); // prefix arama
             }
          }
    }
}
