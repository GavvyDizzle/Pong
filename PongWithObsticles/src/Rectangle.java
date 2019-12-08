import java.util.*;

public class Rectangle 
{
    private double width;
    private double height;
    private double xPos;
    private double yPos;
    public static String color = "White";
    //private Date date;

Rectangle() {
    width = 1;
    height = 1;
    //date = new Date(); 
    }

Rectangle (double x, double y, double w, double h) {
	xPos = x;
	yPos = y;
    width = w;
    height = h;
    //date = new Date();
    }

public double getX() {
    return xPos;
    }

public double getY() {
    return yPos;
    }

public void setX(double n) {
    xPos = n;
    }

public void setY(double n) {
    yPos = n;
    }

public double getHeight() {
    return height;
    }

public void setHeight(double h) {
    height = h;
    }

public double getWidth() {
    return width;
    }

public void setWidth(double w) {
    width = w;
    }

public static String getColor() {
    return color;
    }

public static void setColor(String c) {
    color = c;
    }

/*
public Date getDate() {
    return date;
    }

public void setDate (Date d) {
    date = d; 
    }

public double getArea() {
    return width * height;
    }

public double getPerimeter() {
    return 2  * (width + height);
    }

public String toString() {
    String S;
    S = "Rectangle with width of " + width;
    S = S + " and height of " + height;
    S = S + " was created on " + date.toString();
    return S;
    }
    */
}