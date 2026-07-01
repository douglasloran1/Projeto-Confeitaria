package com.projetoconfeitaria.project.security;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException(String message) { super(message); }
}
