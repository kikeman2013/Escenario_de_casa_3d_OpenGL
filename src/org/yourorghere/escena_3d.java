/*
 * escena_3d.java
 *
 * Created on 30. Juli 2008, 16:18
 */

package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLJPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 *
 * @author cylab
 * @author mbien
 */
public class escena_3d extends JFrame implements KeyListener,
        MouseListener , MouseMotionListener , ActionListener, Runnable{

    private Animator animator;
    GLRenderer gl = new GLRenderer();
    Thread t;
    int vi=0 , vj=0;
    /** Creates new form MainFrame */
    public escena_3d() {
        initComponents();
        setSize(800,800);
        setLocationRelativeTo(null);
        setTitle("Simple JOGL Application por kikeman");
        panel.addGLEventListener(gl);
        animator = new Animator(panel);
        t = new Thread(this);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        animator.start();
        
    }
    
    @Override
    public void setVisible(boolean show){
        if(!show)
            animator.stop();
        super.setVisible(show);
        if(!show)
            animator.start();
        
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        JLabel label = new JLabel();
        panel = new GLJPanel(createGLCapabilites());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        label.setText("Below you see a GLJPanel");

        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 258, Short.MAX_VALUE)
        
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label))
                .addContainerGap())
        
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private GLCapabilities createGLCapabilites() {       
        GLCapabilities capabilities = new GLCapabilities();
        capabilities.setHardwareAccelerated(true);       
        capabilities.setNumSamples(2);
        capabilities.setSampleBuffers(true);        
        return capabilities;
    }
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }catch(Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.INFO, "can not enable system look and feel", ex);
                }
                
                escena_3d frame = new escena_3d();
                frame.setVisible(true);
            }
        });
    }
    private GLJPanel panel;
    
    
    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        switch (key) {
            case KeyEvent.VK_DIVIDE:
                gl.vista +=.5;
            break;
            case KeyEvent.VK_MULTIPLY:
                gl.vista -=.5;
            break;
            case KeyEvent.VK_LEFT:
                gl.rotateY -=15;
            break;
            case KeyEvent.VK_RIGHT:
                gl.rotateY +=15;
            break;
            case KeyEvent.VK_DOWN:
                gl.rotateX +=15;
            break;
            case KeyEvent.VK_UP:
                gl.rotateX -=15;
            break;
            case KeyEvent.VK_HOME:
                gl.rotateX = gl.rotateY = 0;
            break;
            case KeyEvent.VK_NUMPAD1:
                gl.view_rotx+=10;
                break;
            case KeyEvent.VK_NUMPAD2:
                gl.view_rotx-=10;
            break;
            case KeyEvent.VK_NUMPAD3:
                gl.view_roty+=10;
                break;
            case KeyEvent.VK_NUMPAD4:
                gl.view_roty-=10;
            break; 
            case KeyEvent.VK_NUMPAD5:
                gl.view_rotz+=10;
            break;
            case KeyEvent.VK_NUMPAD6:
                gl.view_rotz-=10;
            break;  
                case KeyEvent.VK_A:                
                if(vj==0)
                gl.encenderventiladortecho=!gl.encenderventiladortecho;
                if(vj==4)
                gl.encenderventiladortecho=!gl.encenderventiladortecho;
                vj++;
            break;
            case KeyEvent.VK_S:
                gl.moverjack=!gl.moverjack;
                if(gl.moverjack&&t.isAlive())
                {gl.jackActivo=true;}
                else{gl.jackActivo=false;}
            break;   
            case KeyEvent.VK_F: 
                gl.encenderventiladorpedestal=!gl.encenderventiladorpedestal;
                if(t.isAlive())
                gl.boton = gl.boton*-1;               
            break;
            case KeyEvent.VK_G: 
                if(vi==0)
                gl.encenderaspasdepedestal=!gl.encenderaspasdepedestal;
                if(vi==4)
                gl.encenderaspasdepedestal=!gl.encenderaspasdepedestal;
                vi++;
            break;
            case KeyEvent.VK_I: 
                if(!t.isAlive())
                t.start();
            break; 
            case KeyEvent.VK_O: 
                t.stop();
                t = new Thread(this);
            break; 
            case KeyEvent.VK_Q: 
                    if(t.isAlive())
                    gl.rsabana=!gl.rsabana;
            break;
                case KeyEvent.VK_W: 
                   if(t.isAlive())
                gl.rcajon=!gl.rcajon;
            break;
             
            default:
                break;
        }
            panel.display();
    }
    public void keyReleased(KeyEvent e) { }
    //-------------------------------------------------------
    private int prevMouseX , prevMouseY;
    //Ubicación previa del mouse durante el arrastre.
    private boolean mouseRButtonDown = false;   
    public void mouseClicked(java.awt.event.MouseEvent e) {}
    public void mousePressed(java.awt.event.MouseEvent me) {
        prevMouseX = me.getX();
        prevMouseY = me.getY();
        if((me.getModifiers() & me.BUTTON3_MASK)!=0){
        mouseRButtonDown = true;
        }
    }
    public void mouseReleased(java.awt.event.MouseEvent me) {
        if((me.getModifiers() & me.BUTTON3_MASK)!=0){
        mouseRButtonDown = false;
        }
    }
    public void mouseEntered(java.awt.event.MouseEvent e) {}
    public void mouseExited(java.awt.event.MouseEvent e) {}
    public void mouseDragged(java.awt.event.MouseEvent me) {
    int x = me.getX();
    int y = me.getY();
    Dimension size = me.getComponent().getSize();
    float thetaY = 360.0f * ((float) (x-prevMouseX)/(float)size.width);
    float thetaX = 360.0f * ((float) (prevMouseY-y)/(float)size.height);
    prevMouseX = x;
    prevMouseY = y;
    gl.view_rotx += thetaX;
    gl.view_roty += thetaY;
    }
    public void mouseMoved(java.awt.event.MouseEvent e) {}
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void run() {
        int i =1 , v=20 ,v2=20;
        while(true){
            
            if(gl.encenderventiladorpedestal){
                 
                 if(gl.rotarventiladorpedestal==40)
                     i=-1;
                 if(gl.rotarventiladorpedestal==-40)
                     i=1;
                gl.rotarventiladorpedestal+=5*i;
            }
            
            if(gl.encenderaspasdepedestal && vi!=5){
                switch(vi){
                    case 1: v =20; 
                        break;
                    case 2: v =30; 
                        break;  
                    case 3: v =40; 
                        break;  
                    case 4: vi = 0;
                        gl.encenderaspasdepedestal=!gl.encenderaspasdepedestal;
                        break; 
                }
                gl.rotaraspaspedestal+=v;
            }
            if(gl.encenderventiladortecho && vj!=5){
                switch(vj){
                    case 1: v2 =20; 
                        break;
                    case 2: v2 =30; 
                        break;  
                    case 3: v2 =40; 
                        break;  
                    case 4: vj = 0;
                        gl.encenderventiladortecho=!gl.encenderventiladortecho;
                        break; 
                }
                gl.rotarventidalor+=v2;
            }
            if(gl.moverjack){
                gl.rotarjack+=20;
                }
            if(gl.rsabana){
                gl.rotarsabana-=10;
                }
            else{
                gl.rotarsabana+=10;
            }
            if(gl.rcajon){
                gl.movercajon+=20;
                }
            else{
                gl.movercajon-=20;
            }
            
            panel.display();
        try {                
            t.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(escena_3d.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
}
