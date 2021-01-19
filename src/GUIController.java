import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class that manages the interactions between the customer and the
 * system. It contains numerous frame classes that are used to display and
 * receive different sets of information to and from the customer.
 * 
 * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
 */
public class GUIController {

  private MainMenuFrame main_frame;
  private RegisterFrame register_frame;
  private PaymentFrame payment_frame;
  private CancelFrame cancel_frame;
  private LoginFrame login_frame;
  private SelectionFrame select_frame;
  private TheatreController theatreControl;
  private User theUser;
  private TicketController ticketControl;
  private PaymentController paymentControl;
  private DataBaseController DBControl;

  public GUIController(MainMenuFrame main, RegisterFrame reg, PaymentFrame pay, CancelFrame cancel, LoginFrame login,
      SelectionFrame select, TheatreController theatreCon, PaymentController payCon, TicketController ticCon,
      User theUser1, DataBaseController dbc) {

    // create listener for main menu's buttons
    main_frame = main;
    main_frame.addGuestListener(new MainListener(main_frame));
    main_frame.addRegisterListener(new MainListener(main_frame));
    main_frame.addSignInListener(new MainListener(main_frame));
    main_frame.addCancelTicketListener(new MainListener(main_frame));

    // create listener for regsiter's buttons
    register_frame = reg;
    register_frame.addRegisterListener(new RegisterListener(register_frame));
    register_frame.addCancelListener(new RegisterListener(register_frame));

    // create listener for payment's buttons
    payment_frame = pay;
    payment_frame.addBackListener(new PaymentListener(payment_frame));
    payment_frame.addCancelTicketListener(new PaymentListener(payment_frame));
    payment_frame.addConfirmListener(new PaymentListener(payment_frame));

    // create listener for cancel's buttons
    cancel_frame = cancel;
    cancel_frame.addBackListener(new CancelListener(cancel_frame));
    cancel_frame.addConfirmListener(new CancelListener(cancel_frame));

    // create listener for login's buttons
    login_frame = login;
    login_frame.addAnnouncementListener(new LoginListener(login_frame));
    login_frame.addBookListener(new LoginListener(login_frame));
    login_frame.addCancelTicketListener(new LoginListener(login_frame));

    // create listener for select's buttons
    select_frame = select;
    SelectionListener sl = new SelectionListener(select_frame);
    select_frame.addBackListener(sl);
    select_frame.addConfirmListener(sl);
    select_frame.addConfirmTheatreListener(sl);
    select_frame.addConfirmMovieListener(sl);
    select_frame.addConfirmShowtimeListener(sl);
    select_frame.addConfirmSeatListener(sl);

    theatreControl = theatreCon;
    paymentControl = payCon;
    ticketControl = ticCon;
    theUser = theUser1;
    DBControl = dbc;

    //populate theatre choices 
    select_frame.addAllTheatre(theatreCon.getTheatres());
  }

  class MainListener implements ActionListener {
    private MainMenuFrame main;

    public MainListener(MainMenuFrame m) {
      main = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == main.getRegister()) {
        System.out.println("main register");
        register_frame.setVisible(true);
        main.setVisible(false);
        main.resetText();
      }

      else if (e.getSource() == main.getGuest()) {
        System.out.println("main guest login");
        select_frame.setVisible(true);
        main.setVisible(false);
        main.resetText();
      }

      else if (e.getSource() == main.getSignIn()) {
        System.out.println("main sign in");
        if (DBControl.verifyUser(main.getUsername(), main.getPassword())) {
          //login_frame.setGreeting(((RegisteredUser)theUser).getName());
          //login_frame.setCredit(69.00);
          login_frame.setVisible(true);
          main.setVisible(false);
          main.resetText();
        }
      }

      else if (e.getSource() == main.getCancelTicket()) {
        System.out.println("main cancel");
        main.setVisible(false);
        // getEmailAddress cannot be resolved or is not a field
        if (theUser instanceof RegisteredUser)
          cancel_frame.setRegisteredEmail(((RegisteredUser)theUser).getEmailAddress());
        cancel_frame.setVisible(true);
        main.resetText();
      }
    }
  }

  class RegisterListener implements ActionListener {
    private RegisterFrame regis;

    public RegisterListener(RegisterFrame r) {
      regis = r;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == regis.getRegister()) {
        theUser.register(regis.getFirstName() + regis.getLastName(), regis.getAddress(), regis.getEmail(),regis.getPhone(),regis.getCardType(),regis.getCard(),regis.getExpiryDate());
        // getPassword, getUsername cannot be resolved or is not a field

        DBControl.registerUser(regis.getUsername(), regis.getPassword(), ((RegisteredUser)theUser));
        System.out.println("register complete");
        // getControl().setDataFromGUI("quit");
        regis.dispose();
        login_frame.setVisible(true);
        regis.resetText();
      }

      else if (e.getSource() == regis.getCancel()) {
        System.out.println("register cancel");
        regis.dispose();
        regis.resetText();
        main_frame.setVisible(true);
      }

    }
  }

  class PaymentListener implements ActionListener {
    private PaymentFrame pay;

    public PaymentListener(PaymentFrame p) {
      pay = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == pay.getBack()) {
        System.out.println("pay back");
        // getControl().setDataFromGUI("quit");
        pay.dispose();
        pay.resetText();
        select_frame.setVisible(true);
      }

      else if (e.getSource() == pay.getCancelTicket()) {
        System.out.println("pay cancel");
        pay.dispose();
        pay.resetText();
        cancel_frame.setVisible(true);
      }

      else if (e.getSource() == pay.getConfirm()) {
        System.out.println("pay confirm");
        pay.dispose();
        pay.resetText();
        // do something with payment then send email
        main_frame.setVisible(true);
      }

    }
  }

  class CancelListener implements ActionListener {
    private CancelFrame c;

    public CancelListener(CancelFrame cc) {
      c = cc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == c.getBack()) {
        System.out.println("cancel back");
        c.dispose();
        c.resetText();
        main_frame.setVisible(true);
      }

      else if (e.getSource() == c.getConfirm()) {
        System.out.println("cancel cancel");
        String email = ticketControl.cancelTicket(c.getEmail(), c.getTicketNumber());
        c.displayEmail(email);
        c.dispose();
        c.resetText();
        main_frame.setVisible(true);

      }

    }
  }

  class LoginListener implements ActionListener {
    private LoginFrame log;

    public LoginListener(LoginFrame l) {
      log = l;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == log.getBook()) {
        System.out.println("login book");
        log.dispose();
        select_frame.setVisible(true);
      }

      else if (e.getSource() == log.getAnnouncementButton()) {
        System.out.println("login announce");
        log.displayAnnouncement();
      }

      else if (e.getSource() == log.getCancelTicket()) {
        System.out.println("login cancel");
        log.setVisible(false);
        if (theUser instanceof RegisteredUser)
          cancel_frame.setRegisteredEmail(((RegisteredUser)theUser).getEmailAddress());
        cancel_frame.setVisible(true);
      }
    }
  }

  class SelectionListener implements ActionListener {
    private SelectionFrame sel;
    private Theatre currentTheatre = null;
    private Movie currentMovie = null;
    private Showtime currentShowtime = null;
    private Seat currentSeat = null;

    public SelectionListener(SelectionFrame s) {
      sel = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == sel.getBack()) {
        System.out.println("select back");
        main_frame.setVisible(true);
        sel.resetAllList();
        sel.dispose();
      }

      else if (e.getSource() == sel.getConfirm()) {
        System.out.println("select confirm");
        String[] str = sel.getTicketInfo();
        if (theUser instanceof RegisteredUser)
          // getEmailAddress cannot be resolved or is not a field
          payment_frame.setRegisteredEmail(((RegisteredUser)theUser).getEmailAddress());
        payment_frame.setVisible(true);
        sel.resetAllList();
        sel.dispose();
      }

      else if (e.getSource() == sel.getConfirmTheatre()) {
        for(Theatre t : theatreControl.getTheatreAL()){
          if(sel.getCurrentTheatre().equals(t.getName())){
            currentTheatre = t;
            sel.addAllMovie(t.getMoviesArray());  
          }
        }
      }

      else if (e.getSource() == sel.getConfirmMovie()) {
        if(currentTheatre != null){
          sel.resetShowtimeList();
          for(Movie m : currentTheatre.getMovies()){
            if(sel.getCurrentMovie().equals(m.getName())){
              currentMovie = m;
              sel.addAllShowtime(m.getShowtimeArray());
            }
          }
        }
      }

      else if (e.getSource() == sel.getConfirmShowtime()) {
        if(currentMovie != null){
          sel.resetSeatList();
          for(Showtime s : currentMovie.getShowTime()){
            if(sel.getCurrentShowtime().equals(s.toString())){
              currentShowtime = s;
              sel.addAllSeat(s.getSeatArray());
            }
          }
        }
      }
      else if (e.getSource() == sel.getConfirmSeat()) {
        if(currentShowtime != null){
          for(Seat se : currentShowtime.showAvailable()){
            if(sel.getCurrentSeat().equals(se.toString())){
              currentSeat = se;
              String [] str = sel.getCurrentSeat().split("[: ]+");
              sel.setCost(str[7]);
            }
          }
        }
      }
    }
  }
  
  public void setTheUser(RegisteredUser u){
    theUser = u;
  }
}