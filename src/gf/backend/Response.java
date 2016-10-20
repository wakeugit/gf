package gf.backend;

import java.util.ArrayList;


public class Response<T> {

    T body;
    ArrayList<Exception> exceptions = new ArrayList<>();

}
