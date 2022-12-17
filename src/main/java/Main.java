import Model.Data;
import Model.Extradition;
import Model.Reader;
import org.apache.commons.codec.binary.Hex;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;


class Owner
{
    public Reader reader = null;
}


public class Main {

    static class TOwn
    {
        public static Reader reader = null;
    }

    static class Data {
        public static Model.Data data = null;
    }





    public static void main (String [] args) throws SQLException {

        JFrameLogin JFrameMainWindowLibrarian = new JFrameLogin();

    }
}

