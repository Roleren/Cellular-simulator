package Runner;

import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

class Cam extends PerspectiveCamera {
    Translate t  = new Translate();
    Translate p  = new Translate();
    Translate ip = new Translate();
    Rotate rx = new Rotate();
    { rx.setAxis(Rotate.X_AXIS); }
    Rotate ry = new Rotate();
    { ry.setAxis(Rotate.Y_AXIS); }
    Rotate rz = new Rotate();
    { rz.setAxis(Rotate.Z_AXIS); }
    Scale s = new Scale();
    
    public Cam(Painter mainFrame) { super();
    getTransforms().addAll(t, p, rx, rz, ry, s, ip);
    }
}
    

    
