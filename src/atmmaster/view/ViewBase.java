/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmmaster.view;

/**
 *
 * @author Rayhan Azka  <rayhan.azka.tif418@polban.ac.id>
 */
public class ViewBase {
    protected Keypad keypad;
    protected Screen screen;
    
    public ViewBase (Keypad keypad, Screen screen) {
        this.keypad = keypad;
        this.screen = screen;
    }
}
