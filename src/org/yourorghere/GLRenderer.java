package org.yourorghere;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class GLRenderer implements GLEventListener {
    public float view_rotx = 0.0f , view_roty = 0.0f , view_rotz = 0.0f;
    public float rotateX = 0.0f , rotateY = 0.0f , movz =0.0f , movx=0.0f , movy = 0.0f;
    public float vista =0.0f;
    float[] mat_difusse = {0.6f , 0.6f , 0.0f };
    float[] mat_ambient_naranja = { 0.80f , 0.40f , .0f , 0.0f };
    float[] mat_ambient_verde_chicle = { .43f , .85f , .48f , 0.0f };
    float[] mat_ambient = { 0.7f , 0.7f , 0.7f , 1.0f };
    float[] mat_ambient_violeta = { 1.0f , 0.0f , 1.0f , 0.0f };
    float[] mat_ambient_verde = { 0.0f , 1.0f , 0.0f , 0.0f };
    float[] mat_ambient_rojo = { 1.0f , 0.0f , 0.0f , 0.0f };
    float[] mat_ambient_azul = { 0.0f , 0.0f , 1.0f , 0.0f };
    float[] mat_ambient_azul_vidrio = { 0.0f , 0.0f , 1.0f , 0.3f };
    float[] mat_ambient_amarillo = { 3.0f , 3.0f , 0.0f , 0.0f };
    float[] mat_ambient_rojiso = { 10.0f , 0.35f , 0.0f , 0.0f };
    float[] mat_ambient_negro = { 0.0f , 0.0f , 0.0f , 5.0f };
    float[] mat_ambient_cremita = {1.0f , 1.0f , 0.749f , 0.0f};
    float[] mat_ambient_turquesa = {0.0f , 0.77f , 0.80f , 0.0f};
    float[] mat_ambient_cyan = {0.0f , 0.96f , 1.0f , 0.0f};
    float[] mat_ambient_cafe = {0.62f , 0.44f , 0.23f , 0.0f};
    float[] mat_specular = { 1.0f , 1.0f , 0.0f , 1.0f };
    float[] mat_shininess = { 50.0f };
    float[] lightIntensity = { 0.7f , 0.7f , 0.7f , 1.0f };
    float[] light_position = { 0.0f , 5.0f , 0.0f , 1.0f };
    float[] ambientLight = { 0.4f , 0.4f , 0.4f , 1.0f };
    float[] difuseLight = { 0.7f , 0.7f , 0.7f , 1.0f };
    float[] specular = { 0.9f , 0.9f , 0.9f , 1.0f };
    float[] lightPos = { 0.0f , 2.0f , 0.0f , 1.0f };
    float[] specref = { 0.0f , 0.6f , 0.6f , 1.0f };
    float[] sport_dir = {0.0f ,0.0f , 0.0f , -1.0f};
    //_______________________________________
    //variables de movimientos
    double rotarventidalor = 0, rotarjack = 0, rotarventiladorpedestal =0 , rotaraspaspedestal=0;
    double boton =0.03;
    boolean jackActivo = false  , encenderventiladorpedestal = false;
    boolean encenderventiladortecho = false;
    boolean encenderaspasdepedestal = false;
    boolean moverjack = false , rsabana = false ,rcajon = false;
    double rotarsabana = 0;
    double movercajon = 0;

    
    public void ColorNaranjar(GLAutoDrawable drawable)
    {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient , 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_ambient_amarillo , 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, mat_specular , 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SHININESS, mat_shininess , 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_ambient_naranja , 0);                   
    }
    
    
    float Scale = 1f ;
    float XSteps = 10.0f; //100.0f; tamaño x de la malla
    float ZSteps = 10.0f; //100.0f; tamaño y de la malla
     
    void Rejilla (GLAutoDrawable drawable , float  DEF_floorGridScale ,
            float DEF_floorGridXSteps , float DEF_floorGridZSteps)
    {
    GL gl = drawable.getGL();
    //GLUT glut = new GLUT();
    float zExtent , xExtent , xlocal , zlocal ;
    int loopX , loopZ ;
    gl.glPushMatrix();
        gl.glPushAttrib(GL.GL_LIGHTING_BIT);
            gl.glDisable(GL.GL_LIGHTING);
            gl.glColor3f( 0.50f , 0.50f , 0.50f );
            gl.glLineWidth(1);
            gl.glBegin(GL.GL_LINES);
            xExtent = DEF_floorGridScale * DEF_floorGridXSteps;
            zExtent = DEF_floorGridScale * DEF_floorGridZSteps;
        for (loopX = (int)-DEF_floorGridXSteps ; loopX <= DEF_floorGridXSteps; loopX++) {
            xlocal = DEF_floorGridScale * loopX ; 
            gl.glVertex3f( xlocal , 0.0f , -zExtent );
            gl.glVertex3f( xlocal , 0.0f ,  zExtent );
        }
        for (loopZ = (int)-DEF_floorGridZSteps ; loopZ <= DEF_floorGridZSteps; loopZ++){
            zlocal = DEF_floorGridScale * loopZ ; 
            gl.glVertex3f( -xExtent , 0.0f , zlocal );
            gl.glVertex3f( xExtent , 0.0f ,  zlocal );        
        }
            gl.glEnd();
        gl.glPopAttrib();
        gl.glEnable(GL.GL_LIGHTING);
    gl.glPopMatrix();
    }
    private final GLUT glut = new GLUT();
    
    
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        gl.setSwapInterval(1);
        SetupLucesMateriales(drawable);
        gl.glShadeModel(GL.GL_SMOOTH);
        gl.glEnable(GL.GL_DEPTH_TEST);
    gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);        
    }
    
    public void SetupLucesMateriales(GLAutoDrawable drawable)
    {
        GL gl = drawable.getGL();
        // valores de luz y coordenadas
        float[] ambientLight = {0.4f , 0.4f , 0.4f , 1.0f };
        float[] diffuseLight = {0.7f , 0.7f , 0.7f , 1.0f };
        float[] specular = {0.9f , 0.9f , 0.9f , 1.0f };
        float[] lightPos = {-50.0f , 200.0f , 200.0f , 1.0f };
        float[] specref = {0.0f , 0.6f , 0.6f , 1.0f };
        gl.glEnable(GL.GL_DEPTH_TEST); //hidden surface removal
        gl.glEnable(GL.GL_LIGHTING_BIT);
        gl.glEnable(GL.GL_NORMALIZE);
        gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, ambientLight, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambientLight, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuseLight, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, specular, 0);
        // position an turn on the light
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, lightPos, 0);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        
        if (height <= 0) { // avoid a divide by zero error!        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        //double winHt = 1.0;
        //gl.glOrtho(-winHt*64/48.0, winHt*64/48.0, -winHt, winHt, 0.1,100.0);
        glu.gluPerspective(80.0f, h, 1.0, 30.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        glu.gluLookAt(2.3+vista, 2.3+vista, 3+vista , 0, .2, 0, 0.0, 1.0, 0.0);
        SetupLucesMateriales(drawable);

        gl.glRotatef(-view_rotx , 1.0f , 0.0f , 0.0f );
        gl.glRotatef(view_roty , 0.0f , 1.0f , 0.0f );
            gl.glRotatef(view_rotz , 0.0f , 0.0f , 1.0f );
        trazarEjes(drawable);
        Rejilla(drawable, Scale, XSteps, ZSteps);
    
    //********************************************************************
    //comienza el dibujo
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!PRIMER PISO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//
gl.glPushMatrix();
    //------------------------------PRIMER CUARTO----------------------//    
    gl.glPushMatrix();
        gl.glPushMatrix();
            gl.glTranslated(0.4, 0.4, 0.6);
            gl.glRotated(45, 0, 0, 1);
            gl.glScaled(0.08, 0.08, 0.08);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_amarillo,0);
            if(jackActivo){
                gl.glRotated(40, 0 , 0 , 1 );
                gl.glRotated(rotarjack, 1 , 0 , 0 );
            }
            jack(drawable); //Dibuja el jack
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(0.6, 0.38, 0.5);
            gl.glRotated(30,0,1,0);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_violeta,0);
            glut.glutSolidTeapot(0.08); //Dibuja la tetera
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(0.25, 0.42, 0.35);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_naranja,0);
            glut.glutSolidSphere(0.1, 15, 15);//Dibujar esfera
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(0.4, 0, 0.4);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_azul,0);
            table(drawable,0.6,0.02,0.02,0.3); //dibuja la mesa
        gl.glPopMatrix();
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);
            wall(drawable,0.02); //pared en el plano xz
        gl.glPushMatrix();
            gl.glRotated(90,0.0,0.0,1.0);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cafe,0);
            wall(drawable,0.02); //pared en el plano yz
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glRotated(-90.0, 1.0, 0.0,0.0);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cafe,0);
            wall(drawable,0.02); //pared en el plano xy
        gl.glPopMatrix();  
        gl.glPushMatrix();
            gl.glTranslated(0, -.16, 0);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_verde_chicle,0);
            ventilador(drawable);//ventilador
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(.3, .4, .23);
            gl.glRotated(-90, 1, 0, 0);
            gl.glScaled(0.2, 0.2, 0.2);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_turquesa,0);
            estrella(drawable);//estrella
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(1, 0, 0);
            gl.glRotated(90.0, 0.0, 0.0,1.0);
            walltransparente(drawable); //pared vidrio 
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(1, 0, 1);
            gl.glRotated(90.0, 0.0, 0.0,1.0);
            gl.glRotated(-90.0, 1.0, 0.0,0.0);
            walltransparente(drawable); //pared vidrio 
        gl.glPopMatrix();
    gl.glPopMatrix();
    //------------------------------SEGUNDO CUARTO----------------------//
    gl.glPushMatrix();   
        gl.glTranslated(-1, 0, 0);
        gl.glPushMatrix();
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);           
            wall(drawable,0.02); //pared en el plano xz
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cafe,0);           
            gl.glRotated(-90,1.0,0.0,0.0);
            wall(drawable,0.02); //pared en el plano yx
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(.8, .03, .2);
            gl.glScaled(.8, .8, .8);
            escaleraCaracol(drawable);//escalera de caracol
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(.3, 0, .2);
            gl.glRotated(-90, 0, 1, 0);
            gl.glScaled(.4,.6,.6);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_rojo,0);
            ventiladorpedestal(drawable);//ventilador pedestal
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(.86, 0, 0.7);
            gl.glRotated(-90, 0, 1, 0);
            gl.glScaled(0.3, 0.3, 0.2);
            silla(drawable);//mesa con sillas
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(.4, 0, 0.7);
            gl.glRotated(-90, 0, 1, 0);
            gl.glScaled(0.3, 0.3, -0.2);
            silla(drawable);//mesa con sillas
        gl.glPopMatrix();
    gl.glPopMatrix();
    //------------------------------TERCER CUARTO----------------------//
    gl.glPushMatrix();
        gl.glTranslated(0, 0, -1);
        gl.glPushMatrix();
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);
            wall(drawable,0.02); //pared en el plano xz
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cafe,0);
            gl.glRotated(90,0.0,0.0,1.0);
            wall(drawable,0.02); //pared en el plano yz
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(.5, 0, 0.5);
            gl.glScaled(0.5, 0.5, 0.5);
            mesasillas(drawable);//mesa con sillas
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(0, -.16, 0);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cafe,0);
            ventilador(drawable);//ventilador
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(1, 0, 0);
            gl.glRotated(90.0, 0.0, 0.0,1.0);
            walltransparente(drawable); //pared vidrio 
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(1, 0, 0);
            gl.glRotated(90.0, 0.0, 0.0,1.0);
            gl.glRotated(-90, 1, 0, 0);
            walltransparente(drawable); //pared vidrio 
        gl.glPopMatrix();
    gl.glPopMatrix();
    //------------------------------4° CUARTO----------------------//
    gl.glPushMatrix();
    gl.glTranslated(-1, 0, -1);
        gl.glPushMatrix();
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);
            wall(drawable,0.02); //pared en el plano xz
        gl.glPopMatrix();  
        gl.glPushMatrix();
             gl.glTranslated(.5, 0, .04);
            escaleraEsquina(drawable);//escalera esquinada
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(.5, 0, 0.86);
            gl.glRotated(180, 0, 1, 0);
            gl.glScaled(0.3, 0.3, 0.2);
            silla(drawable);//mesa con sillas
        gl.glPopMatrix();
    gl.glPopMatrix(); 
gl.glPopMatrix();
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SEGUNDO PISO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//
gl.glPushMatrix();
    gl.glTranslated(0, 1, 0);
    //------------------------------PRIMER  Y SEGUNDO CUARTO----------------------//
    gl.glPushMatrix();
        gl.glPushMatrix();
            gl.glTranslated(0.51, 0, 0);
            gl.glScaled(.34, .5, .5);
            micuarto(drawable);
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(0.51, .2, 0);
            gl.glScaled(.34, .5, .5);
            cajonero(drawable);//cajonero
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(0.51, .4, 0);
            gl.glScaled(.34, .5, .5);
            cajonero(drawable);//cajonero
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(1, 0, 0);
            gl.glRotated(90, 0, 0, 1);
            walltransparente(drawable);
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(1, 0,-1);
            gl.glRotated(90, 0, 0, 1);
            walltransparente(drawable);
        gl.glPopMatrix();
    gl.glPopMatrix();
    //------------------------------TERCER CUARTO----------------------//
    gl.glPushMatrix();
        gl.glTranslated(-1, 1, 0);
        gl.glPushMatrix();
            gl.glScaled(.8, 1, 1);   
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);
            wall(drawable,0.02); 
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(0, 0, .3);
            gl.glScaled(1, 1, .7);   
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);
            wall(drawable,0.02);        
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glRotated(-90, 1, 0, 0);  
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_verde,0);
            wall(drawable,0.02);        
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(0, 0, 1);
            gl.glRotated(-90, 1, 0, 0);  
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_verde,0);
            wall(drawable,0.02);        
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(0, 0, -1);
            gl.glRotated(-90, 1, 0, 0);  
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_verde,0);
            wall(drawable,0.02);        
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(.5, 0, .6);
            gl.glRotated(180, 0, 1, 0);
            gl.glScaled(.34, .5, .5);
            cajonero(drawable);//cajonero
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(.6, 0, .48);
            gl.glRotated(-90, 0, 1, 0);
            gl.glScaled(.34, .4, .3);
            cama(drawable);
        gl.glPopMatrix();
    gl.glPopMatrix(); 
    //------------------------------4° CUARTO----------------------//
    gl.glPushMatrix(); 
        gl.glTranslated(-1, 1, -1);
        gl.glPushMatrix();
            gl.glScaled(1, 1, .8);   
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);
            wall(drawable,0.02); 
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(-.001, 0, 0);
            gl.glScaled(.6, 1, 1);   
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);
            wall(drawable,0.02);
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(.5, 0, .5);
            gl.glRotated(180, 0, 1, 0);
            gl.glScaled(.34, .5, .5);
            cajonero(drawable);//cajonero
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(.6, 0, .5);
            gl.glRotated(-90, 0, 1, 0);
            gl.glScaled(-.34, .4, .3);
            cama(drawable);
        gl.glPopMatrix();
    gl.glPopMatrix();
       
gl.glPopMatrix(); 
    //*******************************************************************
        gl.glFlush();
    }
public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
public void trazarEjes(GLAutoDrawable drawable){
    GL gl = drawable.getGL();
    gl.glLineWidth(5);
    gl.glPushAttrib(GL.GL_LIGHTING_BIT);
  gl.glDisable(GL.GL_LIGHTING);
        //eje x = rojo
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(-6000.0f, 0.0f, 0.0f);
        gl.glVertex3f(6000.0f, 0.0f, 0.0f);
        gl.glEnd();
        //eje y = verde
        gl.glColor3f(0.0f,1.0f,0.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.0f, -6000.0f, 0.0f);
        gl.glVertex3f(0.0f, 6000.0f, 0.0f);
        gl.glEnd();
        // eje z = azul
        gl.glColor3f(0.0f,0.0f,1.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.0f, 0.0f, -5000.0f);
        gl.glVertex3f(0.0f, 0.0f, 5000.0f);
        gl.glEnd(); 
     gl.glPopAttrib();
    gl.glEnable(GL.GL_LIGHTING);      
    }
public void wall(GLAutoDrawable drawable,double thickness)
{
GL gl = drawable.getGL();
//Dibuja la pared con espesor y tapa = palno xz, esquina en el origen
gl.glPushMatrix();
gl.glTranslated(0.5,0.5 * thickness,0.5);
gl.glScaled(1.0, thickness, 1.0);
glut.glutSolidCube(1.0f);
gl.glPopMatrix();
}
public void walltransparente(GLAutoDrawable drawable)
{
GL gl = drawable.getGL();
//Dibuja la pared con espesor y tapa = palno xz, esquina en el origen
    gl.glPushMatrix();
        gl.glPushAttrib(GL.GL_LIGHTING_BIT);
            gl.glEnable(GL.GL_COLOR_MATERIAL);
            gl.glEnable(GL.GL_BLEND);
            gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);         
            gl.glTranslated(0.5,0.5 * 0.02,0.5);
            gl.glScaled(1.0, 0.02, 1.0);
            gl.glColor4f(0.0f, 0.9f, 1.0f, .3f);
            glut.glutSolidCube(1.0f); 
            gl.glDisable(GL.GL_BLEND);
            gl.glDisable(GL.GL_COLOR_MATERIAL);   
        gl.glPopAttrib();
    gl.glPopMatrix();
    
}
public void tableLeg(GLAutoDrawable drawable,double thick, double len)
{
GL gl = drawable.getGL();    
gl.glPushMatrix();
gl.glTranslated (0, len/2, 0);
gl.glScaled(thick, len, thick);
glut.glutSolidCube(1.0f);
gl.glPopMatrix();
}
public void jackPart(GLAutoDrawable drawable)
{
GL gl = drawable.getGL();    
//dibuja un eje unidad de la figura - una esfera
gl.glPushMatrix();
gl.glScaled(0.2,0.2,1.0);
glut.glutSolidSphere(1,15,15);
gl.glPopMatrix();
gl.glPushMatrix();
gl.glTranslated(0,0,1.2); // bola al final
glut.glutSolidSphere(0.2,15 ,15);
gl.glTranslated(0,0, -2.4);
glut.glutSolidSphere(0.2,15 ,15); //bola al final del otro extremo
gl.glPopMatrix();
}
public void jack(GLAutoDrawable drawable)
{
    GL gl = drawable.getGL();
//dibuja una rama del esferoide
gl.glPushMatrix();
jackPart(drawable);
gl.glRotated(90.0, 0, 1, 0);
jackPart(drawable);
gl.glRotated(90.0, 1, 0, 0);
jackPart(drawable);
gl.glPopMatrix();
}
public void table(GLAutoDrawable drawable,double topWid, double topThick, double legThick, double legLen)
{
    GL gl = drawable.getGL();
// dibuja la mesa la parte suporior y las cuarto patas
gl.glPushMatrix(); //dibuja el tope
gl.glTranslated(0, legLen,0);
gl.glScaled(topWid, topThick, topWid);
glut.glutSolidCube(1.0f);
gl.glPopMatrix();
double dist = 0.95 * topWid/2.0 - legThick / 2.0;
gl.glPushMatrix();
gl.glTranslated(dist, 0, dist);
tableLeg(drawable,legThick, legLen);
gl.glTranslated(0, 0, -2 * dist);
tableLeg(drawable,legThick, legLen);
gl.glTranslated(-2 * dist, 0, 2 * dist);
tableLeg(drawable,legThick, legLen);
gl.glTranslated(0, 0, -2 * dist);
tableLeg(drawable , legThick, legLen);
gl.glPopMatrix();
}
public void ventilador(GLAutoDrawable drawable)
{
GL gl = drawable.getGL();
GLU glu = new GLU();
GLUquadric Cilindro = glu.gluNewQuadric();
double th = 0;
double r2=0.04,r1=0.06,h=0.5;
    gl.glPushMatrix();
        gl.glTranslated(0.5, 1, 0.5);
        gl.glRotated(rotarventidalor, 0, 1, 0);//giro
        for (int i = 0; i < 4; i++) {
            th = i*360/4;
            gl.glPushMatrix();
                gl.glRotated(th, 0, 1, 0);
                gl.glPushMatrix();
                    gl.glRotated(45, 0,0, 1);
                    gl.glScaled(1, 0.2, 0.4);
                    gl.glRotated(45, 0, 0, 1);
                    glu.gluCylinder(Cilindro, r2, r1, h, 4, 10);
                gl.glPopMatrix();
                gl.glTranslated(0, 0, h*.4);
                gl.glRotated(45, 0, 0, 1);
                gl.glRotated(-90, 1, 0, 0);
                gl.glTranslated(0, 0, -0.01);
                glut.glutSolidCylinder(0.04, 0.02, 20, 20);
            gl.glPopMatrix();      
        }
        gl.glRotated(-90, 1, 0, 0);
        gl.glTranslated(0, 0, -0.03);
        glut.glutSolidCylinder(0.04, 0.06, 20, 20);
        glut.glutSolidCylinder(0.01, 0.2, 20, 20);
        gl.glTranslated(0, 0, 0.1);
        glut.glutSolidCylinder(0.06, 0.1, 20, 20);
    gl.glPopMatrix();
}
public void ventiladorpedestal(GLAutoDrawable drawable)
{
GL gl = drawable.getGL();
GLU glu = new GLU();
GLUquadric Cilindro = glu.gluNewQuadric();
double th = 0;
double r2=0.04,r1=0.06,h=0.5;


gl.glPushMatrix();
    gl.glRotated(-90, 1, 0, 0);
    glut.glutSolidCylinder(.2, .06, 20, 20);//base
gl.glPopMatrix();
gl.glPushMatrix();
    gl.glRotated(-90, 1, 0, 0);
    glut.glutSolidCylinder(.03, .7, 20, 20);//poste
gl.glPopMatrix();
gl.glPushMatrix();
gl.glRotated(rotarventiladorpedestal, 0, 1, 0);
gl.glPushMatrix();
    gl.glTranslated(-.1, .8, 0);
    gl.glRotated(90, 0, 1, 0);
    glut.glutSolidCylinder(.1, .25, 20, 20);//motor
gl.glPopMatrix();
gl.glPushMatrix();
    gl.glTranslated(0,boton, 0);
    gl.glTranslated(-.05, .85, 0);
    gl.glRotated(-90, 1, 0, 0);
    gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cremita,0);
    glut.glutSolidCylinder(0.02, .1, 20, 20);//boton rotar
gl.glPopMatrix();
gl.glPushMatrix();
    gl.glTranslated(.3, .8, 0);
    gl.glRotated(-90, 1, 0, 0);
    gl.glScaled(.5, 1, 1);
    gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);
    glut.glutWireSphere(.3, 30, 7);//proteccion
gl.glPopMatrix();
gl.glPushMatrix();
    gl.glTranslated(.3, .8, 0);
    gl.glRotated(90, 0, 0, 1);
    gl.glPushMatrix();
        gl.glRotated(rotaraspaspedestal, 0, 1, 0);//giro
        for (int i = 0; i < 4; i++) {
            th = i*360/4;
            gl.glPushMatrix();
                gl.glRotated(th, 0, 1, 0);
                gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cremita,0);
                gl.glPushMatrix();
                    gl.glRotated(45, 0,0, 1);
                    gl.glScaled(1, 0.2, 0.4);
                    gl.glRotated(45, 0, 0, 1);
                    glu.gluCylinder(Cilindro, r2, r1, h, 4, 10);//aspas
                gl.glPopMatrix();
                gl.glTranslated(0, 0, h*.4);
                gl.glRotated(45, 0, 0, 1);
                gl.glRotated(-90, 1, 0, 0);
                gl.glTranslated(0, 0, -0.01);
                glut.glutSolidCylinder(0.04, 0.02, 20, 20);//punta del aspa
            gl.glPopMatrix();      
        }
        gl.glRotated(-90, 1, 0, 0);
        gl.glTranslated(0, 0, -0.03);
        glut.glutSolidCylinder(0.04, 0.06, 20, 20);
        glut.glutSolidCylinder(0.01, 0.2, 20, 20);
        gl.glTranslated(0, 0, 0.1);
        glut.glutSolidCylinder(0.06, 0.1, 20, 20);
    gl.glPopMatrix();
    gl.glPopMatrix();
gl.glPopMatrix();
}
public void estrella(GLAutoDrawable drawable)
{
GL gl = drawable.getGL();
GLU glu = new GLU();
GLUquadric Cilindro = glu.gluNewQuadric();
double th = 0;
    gl.glPushMatrix();
        gl.glTranslated(0.5, 1, 0.5);
        gl.glRotated(35, 0, 1, 0);
        for (int i = 0; i < 4; i++) {
            th = i*360/4;
            gl.glPushMatrix();
                gl.glRotated(th, 0, 1, 0);
                gl.glPushMatrix();
                    
                    gl.glScaled(1, 0.2, 0.4);
                    gl.glRotated(45, 0, 0, 1);
                    glu.gluCylinder(Cilindro, 0.5, 0.7, 0.5, 4, 10);
                gl.glPopMatrix();
                gl.glTranslated(0, 0, 0.5*0.4);
                gl.glRotated(45, 0, 0, 1);
                gl.glRotated(-90, 1, 0, 0);
                gl.glTranslated(0, 0, -0.01);
                glut.glutSolidCylinder(0.04, 0.02, 20, 20);
            gl.glPopMatrix();      
        }
        gl.glRotated(-90, 1, 0, 0);
        gl.glTranslated(0, 0, -0.03);
        glut.glutSolidCylinder(0.04, 0.06, 20, 20);
        glut.glutSolidCylinder(0.01, 0.2, 20, 20);
        gl.glTranslated(0, 0, 0.1);
        glut.glutSolidCylinder(0.06, 0.1, 20, 20);
    gl.glPopMatrix();
}
public void escaleraCaracol(GLAutoDrawable drawable)
{
GL gl = drawable.getGL();
GLU glu = new GLU();
GLUquadric Cilindro = glu.gluNewQuadric();
double th = 0;
double r2=0.04,r1=0.06,h=0.5;
    gl.glPushMatrix();
        for (int i = 0; i < 32; i++) {
            th = i*360/20;
            gl.glPushMatrix();   
            gl.glTranslated(0, i*.04, 0);
                gl.glRotated(th, 0, 1, 0);
                gl.glPushMatrix();
                    gl.glRotated(10, 0,0, 1);                    
                    gl.glScaled(1, 0.2, 0.4);                   
                    gl.glRotated(45, 0, 0, 1);
                    gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cafe,0);
                    glu.gluCylinder(Cilindro, r2, r1, h, 4, 20);//escalon
                gl.glPopMatrix();   
                gl.glPushMatrix();
                   gl.glTranslated(.001, .08, .18);
                   gl.glScaled(.01, .2, .01);
                   gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);
                    glut.glutSolidCube(1.0f);//barandal
                gl.glPopMatrix();  
                gl.glPushMatrix();
                if(i!=31){
                gl.glTranslated(.001, .18, .18);
                gl.glRotated(110, 0,1, 0); 
                gl.glRotated(-35, 1,0, 0);
                   gl.glRotated(45, 0,0, 1);                    
                    gl.glScaled(.3, .2, .2);                   
                    gl.glRotated(45, 0, 0, 1);
                    gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cafe,0);
                    glu.gluCylinder(Cilindro, .03,.03, h, 10, 20);//linea barandal
                }
                gl.glPopMatrix();
            gl.glPopMatrix();      
        }
        gl.glRotated(-90, 1, 0, 0);
        gl.glTranslated(0, 0, -0.03);
        glut.glutSolidCylinder(0.04, 1.5, 20, 20);
    gl.glPopMatrix();
}
public void escaleraEsquina(GLAutoDrawable drawable)
{
GL gl = drawable.getGL();
GLU glu = new GLU();
GLUquadric Cilindro = glu.gluNewQuadric();
GLUquadric Cilindro2 = glu.gluNewQuadric();
double th = 0;
double r2=0.04,r1=0.04,h=0.5; 
    gl.glPushMatrix();
        for (int i = 0; i < 5; i++) {
            th = i*360/20;
            gl.glPushMatrix();   
            gl.glTranslated(i*.05, i*.035, 0);
                gl.glPushMatrix();
                 
                    gl.glScaled(1, 0.2, 0.4);                   
                    gl.glRotated(45, 0, 0, 1);
                    gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cremita,0);
                    glu.gluCylinder(Cilindro, r2, r1, h, 4, 20);//escalon
                gl.glPopMatrix();   
                gl.glPushMatrix();
                   gl.glTranslated(.001, .08, .18);
                   gl.glScaled(.01, .2, .01);
                   gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);
                    glut.glutSolidCube(1.0f);//barandal
                gl.glPopMatrix();  
                gl.glPushMatrix();
                
                gl.glTranslated(.001, .18, .18);
                gl.glRotated(90, 0,1, 0); 
                gl.glRotated(-35, 1,0, 0);
                   gl.glRotated(45, 0,0, 1);                    
                    gl.glScaled(.3, .2, .2);                   
                    gl.glRotated(45, 0, 0, 1);
                    gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cremita,0);
                    glu.gluCylinder(Cilindro, .03,.03, h, 10, 20);//linea barandal
                
                gl.glPopMatrix();
                gl.glPopMatrix();       
        }
        gl.glPushMatrix();
                gl.glTranslated(.34, .17, .1);
                   gl.glScaled(.25, .02, .2);
                   gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cremita,0);
                    glut.glutSolidCube(1.0f);//descanso 1
        gl.glPopMatrix();
        gl.glTranslated(.47, .21, .18);
        gl.glRotated(-90, 0, 1, 0);
        for (int j = 0; j < 15; j++) {
            th = j*360/20;
                gl.glPushMatrix();   
                gl.glTranslated(j*.04, j*.04, 0);
                    gl.glPushMatrix();                   
                        gl.glScaled(1, 0.2, 0.4);                   
                        gl.glRotated(45, 0, 0, 1);
                        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cremita,0);
                        glu.gluCylinder(Cilindro2, r2, r1, h, 4, 20);//escalon 2
                    gl.glPopMatrix();   
                    gl.glPushMatrix();
                        gl.glTranslated(.001, .08, .18);
                        gl.glScaled(.01, .2, .01);
                        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);
                        glut.glutSolidCube(1.0f);//barandal 2
                    gl.glPopMatrix();
                    gl.glPushMatrix();
                        if(j!=14){
                        gl.glTranslated(.001, .18, .18);
                        gl.glRotated(90, 0,1, 0); 
                        gl.glRotated(-45, 1,0, 0);
                        gl.glRotated(45, 0,0, 1);                    
                        gl.glScaled(.3, .2, .2);                   
                         gl.glRotated(45, 0, 0, 1);
                         gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cremita,0);
                        glu.gluCylinder(Cilindro, .03,.03, h, 10, 20);//linea barandal 2
                        }
                    gl.glPopMatrix();
                gl.glPopMatrix();    
        }
        gl.glPushMatrix();
                gl.glTranslated(.67, .6, .12);
                gl.glRotated(90, 0, 1, 0);
                   gl.glScaled(.25, .02, .2);
                   gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cremita,0);
                    glut.glutSolidCube(1.0f);//descanso 2
        gl.glPopMatrix();
        gl.glTranslated(.77, .6, .18);
        gl.glRotated(-90, 0, 1, 0);
        for (int k = 0; k < 6; k++) {
            th = k*360/20;
                gl.glPushMatrix();   
                gl.glTranslated(k*.04, k*.04, 0);
                    gl.glPushMatrix();                   
                        gl.glScaled(1, 0.2, 0.4);                   
                        gl.glRotated(45, 0, 0, 1);
                        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cremita,0);
                        glu.gluCylinder(Cilindro2, r2, r1, h, 4, 20);//escalon 3
                    gl.glPopMatrix();   
                    gl.glPushMatrix();
                        gl.glTranslated(.001, .08, .18);
                        gl.glScaled(.01, .2, .01);
                        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);
                        glut.glutSolidCube(1.0f);//barandal 3
                    gl.glPopMatrix();
                    gl.glPushMatrix();
                        if(k!=5){
                        gl.glTranslated(.001, .18, .18);
                        gl.glRotated(90, 0,1, 0); 
                        gl.glRotated(-45, 1,0, 0);
                        gl.glRotated(45, 0,0, 1);                    
                        gl.glScaled(.3, .2, .2);                   
                         gl.glRotated(45, 0, 0, 1);
                         gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cremita,0);
                        glu.gluCylinder(Cilindro, .03,.03, h, 10, 20);//linea barandal 3
                        }
                    gl.glPopMatrix();
                gl.glPopMatrix();    
        }
    gl.glPopMatrix();
}
public void micuarto(GLAutoDrawable drawable){
GL gl = drawable.getGL();
gl.glPushMatrix(); 
    float ancho = .05f, alto = 3.0f , largo = 2.0f;
    //cuarto>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
           //piso
           gl.glRotated(90, 1, 0, 0);
           gl.glScaled(alto, 4, ancho);
           gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix(); 
            //pared 1
            gl.glTranslated(0,0.5 * largo,-2);
            gl.glScaled(alto, largo, ancho);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_verde,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix(); 
            //pared 2
            gl.glTranslated(-1.5,0.5 * largo,0);
            gl.glRotated(90, 0, 1, 0);
            gl.glScaled(4, largo, ancho);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_verde,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix(); 
            //pared 3
            gl.glTranslated(0,0.5 * largo,2);
            gl.glScaled(alto, largo, ancho);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_verde,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix();
    cama(drawable);
    gl.glPopMatrix();
    gl.glPushMatrix();
    gl.glScaled(1, 1, -1);
    cama(drawable);
    gl.glPopMatrix();
    gl.glPushMatrix();
    cajonero(drawable);
    gl.glPopMatrix();
    
    gl.glPopMatrix();

}
public void silla(GLAutoDrawable drawable){
GL gl = drawable.getGL();
gl.glPushMatrix();
    gl.glPushMatrix(); //dibuja el asiento
        gl.glTranslated(0, .5 ,0);
        gl.glScaled(1, .05, 1);
        glut.glutSolidCube(1.0f);
    gl.glPopMatrix();
    gl.glPushMatrix(); //dibuja el respaldo
        gl.glTranslated(0, .8 ,-.5);
        gl.glRotated(90, 1, 0, 0);
        gl.glScaled(1, .05, .7);
        glut.glutSolidCube(1.0f);
    gl.glPopMatrix();
    gl.glPushMatrix();
        gl.glTranslated(0, 1.18,-.523);
        glut.glutSolidCylinder(.5, 0.048, 20, 20);
    gl.glPopMatrix();
    gl.glPushMatrix();//dibujar la pata 1
        gl.glTranslated(.48, .25, .48);
        gl.glScaled(.05, .5, .05);
        glut.glutSolidCube(1.0f);
    gl.glPopMatrix();
    gl.glPushMatrix();//dibujar la pata 2
        gl.glTranslated(.48, .25, -.48);
        gl.glScaled(.05, .5, .05);
        glut.glutSolidCube(1.0f);
    gl.glPopMatrix();
    gl.glPushMatrix();//dibujar la pata 3
        gl.glTranslated(-.48, .25, .48);
        gl.glScaled(.05, .5, .05);
        glut.glutSolidCube(1.0f);
    gl.glPopMatrix();
    gl.glPushMatrix();//dibujar la pata 4
        gl.glTranslated(-.48, .25, -.48);
        gl.glScaled(.05, .5, .05);
        glut.glutSolidCube(1.0f);
    gl.glPopMatrix();
gl.glPopMatrix();
}
public void mesasillas(GLAutoDrawable drawable){
GL gl = drawable.getGL();
    gl.glPushMatrix();
        gl.glScaled(1.5, 1.2,1.5 );
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cremita,0);
        table(drawable,0.6,0.02,0.02,0.3);
    gl.glPopMatrix();
gl.glPushMatrix();
gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_negro,0);
    gl.glPushMatrix();
        gl.glTranslated(0, 0, -0.4);
        gl.glScaled(.5, .5, .3);
        silla(drawable);
    gl.glPopMatrix();
    gl.glPushMatrix();
        gl.glTranslated(0, 0, 0.4);
        gl.glScaled(.5, .5, -.3);
        silla(drawable);
    gl.glPopMatrix();
    gl.glPushMatrix();
        gl.glTranslated(0.4, 0, 0);
        gl.glRotated(90, 0, 1, 0);
        gl.glScaled(.5, .5, -.3);
        silla(drawable);
    gl.glPopMatrix();
    gl.glPushMatrix();
        gl.glTranslated(-0.4, 0, 0);
        gl.glRotated(90, 0, 1, 0);
        gl.glScaled(.5, .5, .3);
        silla(drawable);
    gl.glPopMatrix();
gl.glPopMatrix();
}
public void cama(GLAutoDrawable drawable){
    GL gl = drawable.getGL();
    //cama>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    gl.glPushMatrix(); 
            //pata1
            gl.glTranslated(-1.3,0.5 * .5,1.8);
            gl.glScaled(0.1, .5, .1);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_azul,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix();
            //pata2
            gl.glTranslated(-1.3,0.5*0.5,1.0);
            gl.glScaled(0.1, .5, .1);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_azul,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix();
            //pata3
            gl.glTranslated(.3,0.5*0.5,1.8);
            gl.glScaled(0.1, .5, .1);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_azul,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix();
            //pata4
            gl.glTranslated(.3,0.5*0.5,1.0);
            gl.glScaled(0.1, .5, .1);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_azul,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix();
            //Tarima
            gl.glTranslated(-.5,0.5*0.9,1.4);
            gl.glScaled(1.7, .1, .9);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_azul,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix();
            //colchon
            gl.glTranslated(-.5,0.5*1.2,1.4);
            gl.glScaled(1.7, .2, .9);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cremita,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix();
            //almohada
            gl.glTranslated(-1.2,0.5*1.4,1.4);
            gl.glScaled(.3, .2, .6);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_rojo,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix();
            //sabana primera parte
            gl.glTranslated(-.1,0.5*1.4,1.4);
            gl.glScaled(.8, .01, .9);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_rojo,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix();
            //sabana segunda parte movible
            if(rotarsabana>=0)
                rotarsabana=0;
            if(rotarsabana<=-90)
                rotarsabana=-90;
            if(rotarsabana>=-90)
            gl.glTranslated(-rotarsabana/300,-rotarsabana/400,0);
            gl.glTranslated(-.8,0.5*1.4,1.4);
            if(rotarsabana>=-90)
            gl.glRotated(rotarsabana, 0, 0, 1);
            
            
            gl.glScaled(.6, .01, .9);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_rojo,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
}
public void cajonero(GLAutoDrawable drawable){
    GL gl = drawable.getGL();
//cajon>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    gl.glTranslated(.1, 0, .2);
    gl.glPushMatrix(); 
            //pata1
            gl.glTranslated(-1.3,0.5 * .8,-.5);
            gl.glScaled(0.5, .8, .1);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cafe,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix(); 
            //pata2
            gl.glTranslated(-1.3,0.5 * .8,0);
            gl.glScaled(0.5, .8, .1);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cafe,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix(); 
            //tabla superior
            gl.glTranslated(-1.3,0.5 * 1.5,-.25);
            gl.glScaled(0.5, .1, .4);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cafe,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
    gl.glPushMatrix(); 
            //cajon movible
            if(movercajon>=170)
             movercajon=170;   
            if(movercajon>=0&&movercajon<=180)
            gl.glTranslated(movercajon/500, 0, 0);
            gl.glTranslated(-1.3,0.5 * 1.0,-.25);
            gl.glScaled(0.4, .1, .4);
            gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient_cremita,0);
            glut.glutSolidCube(1);
    gl.glPopMatrix();
}
}





