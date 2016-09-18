package gf.backend;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Response<T> {

    T body;
    ArrayList<Exception> exceptions = new ArrayList<>();
}
