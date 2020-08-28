package com.todolist.wunderlist2.exceptions;

public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(String message)
    {
        super("Error from a WunderList 2.0 Application " + message);
    }
}