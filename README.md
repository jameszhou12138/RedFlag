# RedFlag
##实验题目

红旗-9          

## 实验环境

Intellij IDEA、记事本、命令提示符等   

##实验要求

1. 掌握Java多线程的原理和编程方法。

2. 在本实验的编程过程中，对于导弹、拦截器、主界面等应该使用不同的线程，注意线程创建、开始运行和消亡的过程控制。

 

## 实验内容

1．实验背景介绍和系统具体要求

红旗-9是我国第三代中高空中远程防空导弹系统。本实验模拟防空导弹拦截过程。

界面上有5个按钮：

“开始”按钮：游戏开始或游戏继续。开始按压之后，“停止”按钮失效变灰。开始之后，屏幕上出现自左至右和自右至左的导弹，圆形蓝色。出现的高度是随机的（但不能太靠近画板的上下边缘），高度始终保持不变，直至碰到左或右边框后消失。如果被拦截导弹击中，也会和拦截器一起消失。一旦开始，计时器开始计时，并且把秒数记录在窗口的左下角。导弹从边缘出现的时间是随机的，不能有任何时间周期方面的规律。

“停止”按钮：游戏开始之前，这个按钮是失效灰色的。点击“开始”按钮后，这个按钮才可以正常点击生效。停止后，“停止”按钮立即失效变灰，“开始”按钮可以点击生效。停止后，所有的导弹和拦截器都静止不动。计时器停止，但计时秒数不清零，下一次点击“开始”按钮后，计时器在以前保留的计数的基础上增加。

“拦截”按钮：点击“拦截”按钮，会自底向上垂直发出拦截器。拦截器的速度要大于导弹的速度。可以使用相对速度来设置速度。例如，导弹自左至右飞行1秒的话，如果拦截器的相对速度是导弹的2倍，则它应该在0.5秒的时间从画板底部飞行至画板上边缘。可以多次发射，发射多个拦截器。但是运行中的拦截器的数量有一个上限，例如10，当超过这个数时，“拦截”按钮将变灰失效。

“加速”按钮使得所有导弹和拦截器的速度提升一截。速度一共有多个挡，例如5档，当速度在第5档时，“加速”按钮失效变灰。

“减速”按钮使得所有的导弹和拦截器都同时降速。如果处在1档，则“减速”按钮失效变灰。

 

2．用记事本书写一个Java程序

（1）、建立个人子目录

步骤1：建立个人子目录

第一次上机时先在D盘上建立一个以自己学号+姓名为目录名的子目录，如学号为210824109的张三同学，就用“210824109张三”为子目录名。实验完成的源代码、Java字节码和实验报告三个文件都要放在这个文件夹下（称为上交文件夹）。

 

步骤2：建立Java源代码文件

在所建立的文件夹下建立一个记事本文件RedFlag.txt，并把它重命名为RedFlag.java

（2）、编写源代码

步骤1：创建一个公共类RedFlag

要创建的公共类在默认包中，可引入其它的包。所创建的公共类在文件中的一行如下：

public class RedFlag{… …}

步骤2：建立主方法main( )

在类RedFlag的类体中编写主方法：

public static void main(String[] args){… …}

步骤3：编写方法和RedFlag的主方法main( )

主方法用于测试。辅助方法的方法头请参见附件

 

3．调试和运行

（1）、调试

步骤1：使用命令行工具，先进入用所建的目录下。

步骤2：用javac RedFlag.java编译并调试源代码文件。

（2）、运行

使用java RedFlag运行程序。

 

## 实验方法

根据实验内容，将该实验大致分为三个步骤：

（1）编写图形用户界面；

（2）编写导弹类、拦截器类、计时器类和Canvas类；

（3）编写导弹移动线程、拦截器移动线程、导弹生成线程等多个线程。

 

## 实验结果

（1）用命令提示符cmd编译运行程序。（如图1所示）

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps10.jpg) 

图1 编译运行RedFlag

（2）弹出图形用户界面，初始化界面。上侧画布用于展示用户所绘制的图形；下方为控制面板，其中有5个按钮，“start”为开始按钮，“accelerate”为加速按钮，“intercept”为拦截按钮，“decelerate”为减速按钮，“stop”为暂停按钮，此外，还有用于显示时间的“time(s)”文本框和用于显示拦截命中率的“count(s)”的文本框。（如图2所示）

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps11.jpg) 

图2 初始化界面

 

（3）点击“start”按钮，游戏开始。屏幕上出现自左至右和自右至左的导弹，圆形蓝色。出现的高度是随机的，高度始终保持不变，直至碰到左或右边框后消失。如果被拦截导弹击中，也会和拦截器一起消失。一旦开始，计时器开始计时，并且把秒数记录在窗口的左下角。导弹从边缘出现的时间是随机的，不能有任何时间周期方面的规律。（如图3所示）

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps12.jpg) 

图3 游戏开始图

（4）点击“stop”，游戏暂停，需要再点击“start”才能继续游戏。

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps13.jpg) 

图4 设置背景和坐标

（5）点击“accelerate”和“decelerate”可以设置导弹和拦截器的移动速度。共有1~5个等级的速度，每提升1级速度将会加快一倍。默认初始为3级速度。“accelerate”按钮为加速，若5级速度时，“accelerate”按钮失效，再点击“accelerate”不会再加速；“decelerate”为减速，若1级速度时，“decelerate”按钮失效。

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps14.jpg) 

图5 加速减速图

（6）点击“intercept”会发射自底向上垂直发出拦截器。拦截器的速度是导弹的2倍。可以多次发射，发射多个拦截器。但是运行中的拦截器的数量有一个上限为10，当达上限时，“intercept”按钮将变灰失效。

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps15.jpg) 

图6 拦截器发射图

（7）若拦截器和导弹相撞，则两者都会消失。如图7所示。

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps16.jpg)![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17064\wps17.jpg) 

图7 两者相撞图

 

## 结论分析

（1）多线程

① 运行多线程：

在RedFlag类中编写runThreads方法，使得点击“start”按钮和“stop”按钮时可以运行多线程。

```java
//运行多线程
public void runThreads() {
  MissileThread missileThread = new MissileThread();
  InterceptorThread interceptorThread = new InterceptorThread();
  GenerationThread generationThread = new GenerationThread();
  missileThread.start();
  interceptorThread.start();
  generationThread.start();
}
```

 

② 导弹移动线程：

睡眠时间设为50毫秒。调用canvas的crash方法将当前时间有拦截器和导弹相撞的拦截器和导弹都去除掉，再移动所有导弹。

```java
//导弹移动线程
public class MissileThread extends Thread {
  @Override
  public void run() {
   while (status) {
     try {
      sleep(sleepTime);
     } catch (InterruptedException e) {
      e.printStackTrace();
     }
     canvas.crash();
     for (int i = 0; i < canvas.missiles.size(); i++) {
      Missile missile = canvas.missiles.get(i);
      if (missile.isToRight()) {
        int dx = missile.getX() + missile.getSpeed();
        if (dx >= getWidth()) {
         canvas.missiles.remove(i);
         i--;
        } else {
         missile.setX(dx);
         canvas.missiles.set(i, missile);
        }
      } else {
        int dx = missile.getX() - missile.getSpeed();
        if (dx <= 0) {
         canvas.missiles.remove(i);
         i--;
        } else {
         missile.setX(dx);
         canvas.missiles.set(i, missile);
        }
      }
     }
     canvas.repaint();
   }
  }
}
```

 

③ 拦截器移动线程：

睡眠时间设为50毫秒。调用canvas的crash方法将当前时间有拦截器和导弹相撞的拦截器和导弹都去除掉，再移动所有拦截器。

```java
//拦截器移动线程
public class InterceptorThread extends Thread {
  @Override
  public void run() {
   while (status) {
     try {
      sleep(sleepTime);
     } catch (InterruptedException e) {
      e.printStackTrace();
     }
     canvas.crash();
     for (int i = 0; i < canvas.interceptors.size(); i++) {
      Interceptor interceptor = canvas.interceptors.get(i);
      int dy = interceptor.getY() - interceptor.getSpeed();
      if (dy <= 0) {
        canvas.interceptors.remove(i);
        i--;
      } else {
        interceptor.setY(dy);
        canvas.interceptors.set(i, interceptor);
      }
     }
     if (canvas.interceptors.size() < MAX_INTERCEPTOR_NUMBER) {
      interceptButton.setEnabled(true);
     } else {
      interceptButton.setEnabled(false);
     }
     canvas.repaint();
   }
  }
}
```

 

④ 导弹生成线程：

设置睡眠时间为[1000, 1500)毫秒内的随机数，相当于任意时间间隔内生成导弹。生成的导弹的位置在画布内任一位置，若导弹在画布的左侧则向右飞行，在右侧则向左飞行（不然拦截器就碰不到导弹了）。

```java
//导弹生成线程
public class GenerationThread extends Thread {
  @Override
  public void run() {
   while (status) {
     long currentSleepTime = (long) (Math.random() * 1000 + 500);
     try {
      sleep(currentSleepTime);
     } catch (InterruptedException e) {
      throw new RuntimeException(e);
     }
     int x = (int) (Math.random() * canvas.getWidth());
     int y = (int) (Math.random() * canvas.getHeight());
     boolean toRight = (x < canvas.getWidth() / 2);
     Missile missile = new Missile(x, y, canvas.getMissileSpeed(), toRight);
     canvas.missiles.add(missile);
     totalInterceptorNumber++;
     interceptorText.setText(canvas.getHitTimes() + "/" + totalInterceptorNumber);
   }
  }
}
```



（2）类：

① 导弹类：

导弹类中有私有成员变量Color来表示导弹的颜色、x和y表示该导弹的位置、size表示导弹的直径大小、speed表示导弹的速度、toRight表示导弹的方向——true的话向右，false的话向左，构造方法Minssile用于生成一个新的导弹，成员方法toString便于调试输出该导弹的信息以及一些方法用于修改或获取某一成员变量，此外，还有drawMissile方法来绘制导弹。

```java
//导弹类
class Missile {
  private Color color = Color.blue;
  private int x = 0, y = 0;
  private int size = 30;
  private int speed = 4;
  private boolean toRight = true;

  public Missile(int x, int y, int speed, boolean toRight) {
   this.x = x;
   this.y = y;
   this.speed = speed;
   this.toRight = toRight;
  }

  @Override
  public String toString() {
   return "Missile{" +
      "color=" + color +
      ", x=" + x +
      ", y=" + y +
      ", size=" + size +
      ", speed=" + speed +
      ", toRight=" + toRight +
      '}' + "\n";
  }

  public void setColor(Color color) {
   this.color = color;
  }

  public void setX(int x) {
   this.x = x;
  }

  public void setY(int y) {
   this.y = y;
  }

  public void setSize(int size) {
   this.size = size;
  }

  public void setSpeed(int speed) {
   this.speed = speed;
  }

  public void setToRight(boolean toRight) {
   this.toRight = toRight;
  }

  public Color getColor() {
   return color;
  }

  public int getX() {
   return x;
  }

  public int getY() {
   return y;
  }

  public int getSize() {
   return size;
  }

  public int getSpeed() {
   return speed;
  }

  public boolean isToRight() {
   return toRight;
  }

  //绘制导弹
  public void drawMissile(Graphics g) {
   Graphics2D g2 = (Graphics2D) g;
   g2.setPaint(color);
   g2.fillOval(x, y, size, size);
  }
}
```

 

② 拦截器类：

拦截器类中有私有成员变量Color来表示拦截器的颜色、x和y表示该拦截器的位置、size表示拦截器的直径大小、speed表示拦截器的速度，构造方法Interceptor用于生成一个新的拦截器，成员方法toString便于调试输出该拦截器的信息以及一些方法用于修改或获取某一成员变量，此外，还有drawInterceptor方法来绘制拦截器。

```java
//拦截器类
class Interceptor {
  private Color color = Color.red;
  private int x = 0, y = 0;
  private int size = 20;
  private int speed = 8;

  public Interceptor(int x, int y, int speed) {
   this.x = x;
   this.y = y;
   this.speed = speed;
  }

  @Override
  public String toString() {
   return "Interceptor{" +
      "color=" + color +
      ", x=" + x +
      ", y=" + y +
      ", size=" + size +
      ", speed=" + speed +
      '}' + "\n";
  }

  public Color getColor() {
   return color;
  }

  public int getX() {
   return x;
  }

  public int getY() {
   return y;
  }

  public int getSize() {
   return size;
  }

  public int getSpeed() {
   return speed;
  }

  public void setColor(Color color) {
   this.color = color;
  }

  public void setX(int x) {
   this.x = x;
  }

  public void setY(int y) {
   this.y = y;
  }

  public void setSize(int size) {
   this.size = size;
  }

  public void setSpeed(int speed) {
   this.speed = speed;
  }

  //绘制拦截器
  public void drawInterceptor(Graphics g) {
   Graphics2D g2 = (Graphics2D) g;
   g2.setPaint(color);
   g2.fillOval(x, y, size, size);
  }
}
```

 

③ 计时器类：

计时器类中有私有成员变量number来表示当前记录下的时间、status表示导弹的方向——true的话向右，false的话向左，构造方法TimeController用于生成一个新的计时器，一些成员方法用于修改或获取某一成员变量，此外，还有重写run方法来修改timeText中的数值。

```java
//计时器类
class TimeController extends TimerTask {
  private int number;
  private boolean status;

  public TimeController(int number, boolean status) {
   this.number = number;
   this.status = status;
  }

  @Override
  public void run() {
   if (status) {
     timeText.setText(String.valueOf(timeController.getNumber()));
     number++;
   }
  }

  public void setNumber(int number) {
   this.number = number;
  }

  public void setStatus(boolean status) {
   this.status = status;
  }

  public int getNumber() {
   return number;
  }

  public boolean isStatus() {
   return status;
  }
}
```

 
