//package org.company.lab3;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.view.View;
//
//public class Drawer extends View{ // наследуем от View
//    public Drawer(Context context) {
//        super(context); // в конструкторе вызываем конструктор классародителя
//    }
//    @Override
//    protected void onDraw(Canvas c){
//        super.onDraw(c);
//        Paint paint = new Paint(); // создаем экземпляр класса Paint
//        paint.setStyle(Paint.Style.FILL); // используем заливку во всехграфических примитивах
//        // закрашиваем всё белым цветом
//        paint.setColor(Color.WHITE); // указываем, что используем белыйцвет
//        c.drawPaint(paint);
//        // Рисуем зеленый круг
//        paint.setAntiAlias(true); // задаем режим сглаживания
//        paint.setColor(Color.GREEN); // выбираем зеленый цвет
//        c.drawCircle(450, 30, 25, paint); // рисуем круг
//        // Рисуем черный прямоугольник
//        paint.setColor(Color.BLACK); // выбираем черный цвет
//        c.drawRect(20, 200, 460, 230, paint); // рисуем прямоугольник
//        // Рисуем текст
//        paint.setColor(Color.BLUE); // указываем, что используем синий цвет
//        paint.setStyle(Paint.Style.FILL); // используем заливку
//        paint.setAntiAlias(true); // режим сглаживания
//        paint.setTextSize(30); // задаем размер текста
//        c.drawText("Текст", 30, 200, paint); // задаем текст и его координаты
//        // восстанавливаем холст
//        c.restore();
//    }
//}
