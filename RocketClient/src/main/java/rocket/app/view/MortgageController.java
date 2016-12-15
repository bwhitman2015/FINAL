package rocket.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import eNums.eAction;
import exceptions.RateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)
	
	
	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private TextField txtDownPayment;
	@FXML
	private ComboBox<String> loanTerm;
	@FXML
	private Label labellIncome;
	@FXML
	private Label labelExpenses;
	@FXML
	private Label labelCreditScore;
	@FXML
	private Label labelHouseCost;
	@FXML
	private Label labelTerm;
	@FXML
	private Label labelDownPayment;
	@FXML
	private Button btnPayment;
	@FXML
	public Label labelError;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	public void testBtn(ActionEvent event)
	{
		int term = Integer.parseInt(((String) loanTerm.getValue()).substring(0, 2));
		System.out.println(term);
	}
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq

		int term = Integer.parseInt(((String) loanTerm.getValue()).substring(0, 2)) * 12;

		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		lq.setdExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setdIncome(Double.parseDouble(txtIncome.getText()));
		lq.setdRate(Double.parseDouble(txtHouseCost.getText())-Double.parseDouble(txtDownPayment.getText()));
		lq.setiTerm(term);

		a.setLoanRequest(lq);
;
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
	
		double downPayment = 0;
		double piti;
		
		if (lRequest.getdIncome()*.28>(lRequest.getdIncome()*.36) - lRequest.getdExpenses())
		{
			piti = lRequest.getdIncome() * .28;
		}
		else
		{
			piti = ((lRequest.getdIncome(*.36)-lRequest.getdExpenses());
		}
		
		downPayment = piti;
		lRequest.setdPayment(downPayment);

		labelDownPayment.setText(String.valueOf(downPayment));
		
	}
	
	public void initialize(URL location, ResourceBundle resources)
	{
		addComboBoxItems();
	}


	public void addComboBoxItems()
	{
		ObservableList<String> options = FXCollections.observableArrayList("15-Year", "30-Year");
		loanTerm.getItems().addAll(options);

	
		loanTerm.setValue(options.get(0));
	}
		
		public void HandleRateException(RateException e){
			labelError.setText("Your credit score is too low");

		
		
		
	}
}
