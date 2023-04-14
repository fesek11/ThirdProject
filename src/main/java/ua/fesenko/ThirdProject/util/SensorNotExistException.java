package ua.fesenko.ThirdProject.util;

public class SensorNotExistException extends RuntimeException {
    public SensorNotExistException(String msg) {
        super(msg);
    }
}
