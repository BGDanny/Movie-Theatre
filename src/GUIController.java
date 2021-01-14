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

  /**
  * Constructor that takes in various arguments and sets them to the appropriate variables
  * @param main MainMenuFrame responsible for the main menu.
  * @param reg RegisterFrame responsible for when the user registers.
  * @param pay PaymentFrame responsible for when the user makes a payment.
  * @param cancel CancelFrame responsible for when the user cancels a ticket.
  * @param login LoginFrame responsible for when the use has logged in.
  * @param select SelectionFrame responsible for when the user is selecting a showtime.
  * @param theatreCon TheatreController responsible for accessing the list of theatres.
  * @param payCon PaymentController responsible for payments.
  * @param ticCon TicketController responsible for booking and cancelling tickets.
  * @param theUesr1 User object representing the customter.
  * @param dbc DataBaseController responsible for communicating to and from the database.
  */
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
    PaymentListener pl = new PaymentListener(payment_frame);
    payment_frame.addBackListener(pl);
    payment_frame.addCancelTicketListener(pl);
    payment_frame.addConfirmListener(pl);
    payment_frame.addApplyCreditListener(pl);

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

  /**
    * Inner class responsible for managing functionalities of the main menu
    * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
    */  
  class MainListener implements ActionListener {
    private MainMenuFrame main;

    public MainListener(MainMenuFrame m) {
      main = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      /**
       * If the register button is clicked, proceed to Register Frame
       */  
      if (e.getSource() == main.getRegister()) {
        System.out.println("main register");
        register_frame.setVisible(true);
        main.setVisible(false);
        main.resetText();
      }
      /**
       * If the guest button is clicked, proceed to Selection Frame as a Guest
       */  
      else if (e.getSource() == main.getGuest()) {
        System.out.println("main guest login");
        select_frame.setVisible(true);
        main.setVisible(false);
        main.resetText();
      }
      /**
       * If the sign in button is clicked, sign in with the current credentials
       */  
      else if (e.getSource() == main.getSignIn()) {
        System.out.println("main sign in");
        RegisteredUser temp = DBControl.verifyUser(main.getUsername(), main.getPassword());

        if (temp!=null) {
          theUser.loggedIn(temp);
          login_frame.setGreeting(temp.getName());
          login_frame.setVisible(true);
          main.setVisible(false);
          main.resetText();
        }
      }
      /**
       * If the cancel button is clicked, proceed to Cancel a ticket
       */  
      else if (e.getSource() == main.getCancelTicket()) {
        System.out.println("main cancel");
        main.setVisible(false);
        cancel_frame.setVisible(true);
        main.resetText();
      }
    }
  }

    /**
    * Inner class responsible for managing functionalities of the registration frame
    * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
    */  
  class RegisterListener implements ActionListener {
    private RegisterFrame regis;

    public RegisterListener(RegisterFrame r) {
      regis = r;
    }

    @Override
    /**
     * If the register button is clicked, use the information entered to create a new user
     */  
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == regis.getRegister()) {
        theUser.register(regis.getFirstName() + regis.getLastName(), regis.getAddress(), regis.getEmail(),regis.getPhone(),regis.getCardType(),regis.getCard(),regis.getExpiryDate());
        DBControl.registerUser(regis.getUsername(), regis.getPassword(), ((RegisteredUser)theUser));
        System.out.println("register complete");
        regis.dispose();
        login_frame.setVisible(true);
        regis.resetText();
      }
    /**
     * If the cancel button is clicked, return to main menu
     */ 
      else if (e.getSource() == regis.getCancel()) {
        System.out.println("register cancel");
        regis.dispose();
        regis.resetText();
        main_frame.setVisible(true);
      }

    }
  }

    /**
    * Inner class responsible for managing functionalities of the payment frame
    * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
    */  

  class PaymentListener implements ActionListener {
    private PaymentFrame pay;
    private double cost;
    

    public PaymentListener(PaymentFrame p) {
      pay = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    /**
     * If the back button is clicked, return to booking
     */ 
      if (e.getSource() == pay.getBack()) {
        System.out.println("pay back");
        pay.dispose();
        pay.resetText();
        select_frame.setVisible(true);
      }
    /**
     * If the cancel button is clicked, proceed to cancel a ticket
     */ 
      else if (e.getSource() == pay.getCancelTicket()) {
        System.out.println("pay cancel");
        pay.dispose();
        pay.resetText();
        cancel_frame.setVisible(true);
      }
    /**
     * If the confirm button is clicked, proceed to make a payment and display an email with ticket number
     */ 
      else if (e.getSource() == pay.getConfirm()) {
        System.out.println("pay confirm");
        theUser.choosePayMethod(pay.getCardType(), pay.getCard(), pay.getExpiryDate());
        if(theUser.tryPayment()){
          String em = ticketControl.bookTicket(pay.getEmail(), pay.getTotalCost());
          pay.displayEmail(em);
          pay.dispose();
          pay.resetText();
        }
        if(theUser instanceof RegisteredUser)
          login_frame.setVisible(true);
        else 
          main_frame.setVisible(true);
      }

    /**
     * If the apply button is clicked, apply a credit code to the payment
     */ 
      else if(e.getSource() == pay.getApply()){
        System.out.println("pay credit");
        if(ticketControl.getTheCredit() == null && pay.getCreditCode().length()>0){
          System.out.println("Made a credit");
          pay.setCost(ticketControl.addCredit(pay.getCreditCode()));
        }
      }

    }
  }

    /**
    * Inner class responsible for managing functionalities of the cancel ticket frame
    * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
    */  
  class CancelListener implements ActionListener {
    private CancelFrame c;

    public CancelListener(CancelFrame cc) {
      c = cc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    /**
     * If the back button is clicked, return to main menu
     */ 
      if (e.getSource() == c.getBack()) {
        System.out.println("cancel back");
        c.resetText();
        c.dispose();
        main_frame.setVisible(true);
      }
    /**
     * If the confirm button is clicked, cancel the ticket and receive an email with credit
     */ 
      else if (e.getSource() == c.getConfirm()) {
        // The system day is November 9 due to data base construction
        if(DBControl.getTicket(Integer.parseInt(c.getTicketNumber())).getTheShowtime().getDate().getMonth() == 11  && DBControl.getTicket(Integer.parseInt(c.getTicketNumber())).getTheShowtime().getDate().getDay() > 12){
          System.out.println("cancel cancel");
          String email = ticketControl.cancelTicket(c.getEmail(), c.getTicketNumber());
          c.displayEmail(email);
          c.resetText();
          c.dispose();
          main_frame.setVisible(true);
        }
      }

    }
  }

    /**
    * Inner class responsible for managing functionalities of the profile frame
    * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
    */  
  class LoginListener implements ActionListener {
    private LoginFrame log;

    public LoginListener(LoginFrame l) {
      log = l;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    /**
     * If the book button is clicked, proceed to booking
     */ 
      if (e.getSource() == log.getBook()) {
        System.out.println("login book");
        log.dispose();
        select_frame.setVisible(true);
      }
    /**
     * If the announcement button is clicked, display movie announcement
     */ 
      else if (e.getSource() == log.getAnnouncementButton()) {
        System.out.println("login announce");
        log.displayAnnouncement();
      }
    /**
     * If the cancel button is clicked, proceed to cancel a ticket
     */ 
      else if (e.getSource() == log.getCancelTicket()) {
        System.out.println("login cancel");
        log.setVisible(false);
        if (theUser instanceof RegisteredUser)
          cancel_frame.setRegisteredEmail(((RegisteredUser)theUser).getEmailAddress());
        cancel_frame.setVisible(true);
      }
    }
  }

  /**
    * Inner class responsible for managing functionalities of the selection frame
    * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
    */  
  class SelectionListener implements ActionListener {
    private SelectionFrame sel;
    /**
    * Selection Listener keeps track of the current chosen theatre, movie, showtime and seat
    */  
    private Theatre currentTheatre = null;
    private Movie currentMovie = null;
    private Showtime currentShowtime = null;
    private Seat currentSeat = null;

    public SelectionListener(SelectionFrame s) {
      sel = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      /**
     * If the back button is clicked, return to main menu
     */ 
      if (e.getSource() == sel.getBack()) {
        System.out.println("select back");
        main_frame.setVisible(true);
        sel.resetAllList();
        sel.dispose();
      }
      /**
     * If the confirm button is clicked, proceed to payment
     */ 
      else if (e.getSource() == sel.getConfirm()) {
        if(currentTheatre != null && currentMovie != null & currentShowtime != null && currentSeat != null){
          System.out.println("select confirm");
          ticketControl.buildTicket(currentTheatre, currentMovie, currentShowtime, currentSeat);
          if (theUser instanceof RegisteredUser){
            payment_frame.setRegisteredEmail(((RegisteredUser)theUser).getEmailAddress());
            payment_frame.setCardNumber(((RegisteredUser)theUser).getCardNumber());
            payment_frame.setCardType(((RegisteredUser)theUser).getType());
            payment_frame.setExpiryDate(((RegisteredUser)theUser).getExpiryDate());
          }
          payment_frame.setVisible(true);
          sel.resetAllList();
          sel.dispose();
        }
      }
    /**
     * If the apply button next to theatre is clicked, set current theatre to the chosen theatre. Then populate the list of movies this theatre has. If the user is a registered user, also display pre-order movies.
     */ 
      else if (e.getSource() == sel.getConfirmTheatre()) {
        for(Theatre t : theatreControl.getTheatreAL()){
          if(sel.getCurrentTheatre().equals(t.getName())){
            currentTheatre = t;
            if(theUser instanceof RegisteredUser)
              sel.addAllMovie(t.getMoviesArray()); 
            else{
              for(Movie m : currentTheatre.getMovies()){
                if(m.getPublic() == 1)
                  sel.addMovie(m.getName());
              }
            } 
          }
        }
      }
    /**
     * If the apply button next to movie is clicked, set current movie to the chosen movie. Then populate the list of showtimes this movie has
     */
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
    /**
     * If the apply button next to showtime is clicked, set current showtime to the chosen showtime. Then populate the list of seats this showtime has. If the movie is a pre-order movie, only display 10% of seats
     */
      else if (e.getSource() == sel.getConfirmShowtime()) {
        if(currentMovie != null){
          sel.resetSeatList();
          for(Showtime s : currentMovie.getShowTime()){
            if(sel.getCurrentShowtime().equals(s.toString())){
              if(currentMovie.getPublic() == 1){
                currentShowtime = s;
                sel.addAllSeat(s.getSeatArray());
              }
              else if(currentMovie.getPublic() == 0 && theUser instanceof RegisteredUser){
                currentShowtime = s;
                int remaining = s.showAvailable().size() - 18;                
                sel.addPrivateSeat(s.getSeatArray(), remaining);
              }
            }
          }
        }
      }
    /**
     * If the apply button next to seat is clicked, set current seat to the chosen seat, display the cost of the seat.
     */
      else if (e.getSource() == sel.getConfirmSeat()) {
        if(currentShowtime != null){
          for(Seat se : currentShowtime.showAvailable()){
            if(sel.getCurrentSeat().equals(se.toString())){
              currentSeat = se;
              String [] str = sel.getCurrentSeat().split("[: ]+");
              sel.setCost(str[7]);
              payment_frame.setCost(Double.parseDouble(str[7]));
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