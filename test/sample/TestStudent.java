package sample;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.BE.Classes;

public class TestStudent {
    @Test
    public void testStudent(){
        Classes classes = new Classes(1,"easv","datamatiker");
        Assertions.assertEquals("easv",classes.getClassName());
    }

    
}
