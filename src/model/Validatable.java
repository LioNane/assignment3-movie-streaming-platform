package model;

import exception.InvalidInputException;

public interface Validatable {
    public void validate() throws InvalidInputException;
}
