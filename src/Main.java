/**
* The main class of the program holding the main method.
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
class Main {
  /**
  * Main method of the program.
  */
  public static void main(String[] args) {
    
    MainMenuFrame menu = new MainMenuFrame();
    RegisterFrame register = new RegisterFrame();
    PaymentFrame payment = new PaymentFrame();
    CancelFrame cancel = new CancelFrame();
    LoginFrame login = new LoginFrame();
    SelectionFrame select = new SelectionFrame();
    DataBaseController dbcon = new DataBaseController();
    dbcon.initializeConnection();
    TheatreController theatreCon = new TheatreController(dbcon);
    //payment constructor
    PaymentController payCon = new PaymentController();
    TicketController ticCon = new TicketController(dbcon);
    //constructor of user needs gui
    User theUser = new User(theatreCon, dbcon, ticCon, payCon);
    GUIController gui = new GUIController (menu, register, payment, cancel, login, select, theatreCon, payCon, ticCon, theUser, dbcon);
    theUser.assignGUI(gui);

    menu.setVisible(true);
  }
}