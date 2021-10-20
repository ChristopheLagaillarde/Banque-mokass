/*/////////////////En tête////////////////////////////
 * Programme : Gestionnaire de compte bancaire.
 *
 * Description : Ce programme génère un fenêtre permettant
 *               de créer des comptes bancaire, de les afficher
 *               ,créditer,débiter,supprimer, et de vérifier si
 *               une carte bancaire est valide ou pas.
 *
 * Auteur : Christophe LAGAILLARDE
 *
 * Date : 11/05/2021
 *        
 *///////////////////////////////////////////////////

// Déclaration des bibliotheques de fonctions...
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


//////////////////Le Programme principal/////////////

//////////////////Début//////////////////////////////
public class CompteBancaire {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompteBancaire window = new CompteBancaire();
					window.frame.setVisible(true);
				} catch (Exception erreurFenetre) {
					JOptionPane.showMessageDialog(null, "Il y a eu une erreur avec le programme");
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CompteBancaire() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Menu gérer un Compte

		class Interface{

			// Attributs
			String interfaceEcrit;

			JLabel nomEcrit;
			JLabel prenomEcrit;
			JLabel dateNaissanceEcrit;
			JLabel adresseEcrit;
			JLabel choixSexeEcrit;
			JLabel choixTypeCompteEcrit;
			JLabel numeroCompteASupprimerEcrit;
			JLabel numeroCompteADebiterOuCrediterEcrit;
			JLabel montantADebiterOuCrediterEcrit;
			JLabel CompteSelectionneEcrit;
			JLabel ToutCompteEcrit;
			JLabel ToutTypeCompteEcrit;
			JLabel carteAVerifierEcrit;
			JLabel sommeSoldeEcrit;
			JLabel aideEcrit;

			JFormattedTextField barreNom;
			JFormattedTextField barrePrenom;
			JFormattedTextField barreDateNaissance;
			JFormattedTextField barreAdresse;
			JFormattedTextField barreNumeroCompte;
			JFormattedTextField barreMontantDebiterOuCrediter;
			JFormattedTextField barreRechercheCompte;
			JFormattedTextField barreVerifierCarte;

			JRadioButton choixSexeHomme;
			JRadioButton choixSexeFemme;
			JRadioButton choixTypeCompteCourant;
			JRadioButton choixTypeCompteJoint;
			JRadioButton choixTypeCompteEpargne;

			ButtonGroup choixSexe;
			ButtonGroup choixTypeCompte;

			JButton boutonCreeCompte;
			JButton boutonSupprimerCompte;
			JButton boutonAltererCompte;
			JButton boutonAfficherCompte;
			JButton boutonAfficherToutComptes;
			JButton boutonAfficherToutComptesType;
			JButton boutonVerifierCarte;
			JButton boutonValider;
			JButton boutonRetour;

			JTable tableauCompte;
			JScrollPane barreDeDefilement;

			Object[]  titre = {"numéro compte","ID titulaire","Nom","Prénom","Date naissance","Sexe","Adresse","Type de compte","ID titulaire joint","Solde (en $) ","taux (en %)"};
			DefaultTableModel tableau = new DefaultTableModel();


			// Méthodes
			Interface (){

				// Gerer un compte


				// Créer un compte
				interfaceEcrit = new String();
				boutonCreeCompte = new JButton("Créer un compte");
				boutonCreeCompte.setBounds(20, 44, 244, 37);
				frame.getContentPane().add(boutonCreeCompte);

				// Saisie du nom
				nomEcrit = new JLabel("Nom :");
				nomEcrit.setFont(new Font("Arial", Font.BOLD, 14));
				nomEcrit.setBounds(5, 16, 48, 17);
				frame.getContentPane().add(nomEcrit);
				barreNom = new JFormattedTextField();
				barreNom.setBounds(84, 13, 133, 20);	
				barreNom.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent appuyerTouche) {
						char caractere = appuyerTouche.getKeyChar();
						if(caractere == KeyEvent.VK_SPACE || caractere == KeyEvent.VK_MINUS || appuyerTouche.getKeyChar() =='\'' ) { 
							if(barreNom.getText().length() >= 33) {
								appuyerTouche.consume();	
							}
						}
						else if(!(Character.isAlphabetic(caractere)) || (caractere == KeyEvent.VK_BACK_SPACE) || (caractere == KeyEvent.VK_DELETE) || (barreNom.getText().length() >= 35) ){
							appuyerTouche.consume();
						}
					}
				});
				frame.getContentPane().add(barreNom);

				// Saisie du prénom
				prenomEcrit = new JLabel("Prénom :");
				prenomEcrit.setFont(new Font("Arial", Font.BOLD, 14));
				prenomEcrit.setBounds(5, 43, 75, 37);
				frame.getContentPane().add(prenomEcrit);
				barrePrenom = new JFormattedTextField();
				barrePrenom.setBounds(84, 51, 133, 20);
				barrePrenom.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent appuyerTouche) {
						char caractere = appuyerTouche.getKeyChar();
						if(caractere == KeyEvent.VK_SPACE || caractere == KeyEvent.VK_MINUS || appuyerTouche.getKeyChar() =='\'' ) {
							if(barrePrenom.getText().length() >= 33) {
								appuyerTouche.consume();
							}
						}
						else if(!(Character.isAlphabetic(caractere)) || (caractere == KeyEvent.VK_BACK_SPACE) || (caractere == KeyEvent.VK_DELETE) || (barrePrenom.getText().length() >= 35)  ){
							appuyerTouche.consume();
						}
					}
				});
				frame.getContentPane().add(barrePrenom);

				// Saisie de la date de naissance
				dateNaissanceEcrit = new JLabel("Née le :");
				dateNaissanceEcrit.setFont(new Font("Arial", Font.BOLD, 14));
				dateNaissanceEcrit.setBounds(5, 92, 100, 17);
				frame.getContentPane().add(dateNaissanceEcrit); 
				barreDateNaissance = new JFormattedTextField();
				barreDateNaissance.setBounds(84, 89, 133, 20);   
				barreDateNaissance.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent appuyerTouche) {
						char caractere = appuyerTouche.getKeyChar();
						if(barreDateNaissance.getText().length() <=1 ) {
							if(!(Character.isDigit(caractere))) {
								appuyerTouche.consume();
							}
						}
						if(barreDateNaissance.getText().length() == 2) {
							if(!(appuyerTouche.getKeyChar() =='/')) {
								appuyerTouche.consume();
							}
						}
						if(barreDateNaissance.getText().length() > 2 && barreDateNaissance.getText().length() < 5 ) {
							if(!(Character.isDigit(caractere))) {
								appuyerTouche.consume();
							}
						}

						if(barreDateNaissance.getText().length() == 5) {
							if(!(appuyerTouche.getKeyChar() =='/')) {
								appuyerTouche.consume();
							}
						} 
						if(barreDateNaissance.getText().length() >= 6 && barreDateNaissance.getText().length() < 10) {
							if(!(Character.isDigit(caractere))) {
								appuyerTouche.consume();
							}
						}
						if(barreDateNaissance.getText().length() >= 10) {
							appuyerTouche.consume();
						}
					}
				});
				frame.getContentPane().add(barreDateNaissance);

				// Saisie de l'adresse
				adresseEcrit = new JLabel("Adresse :");
				adresseEcrit.setFont(new Font("Arial", Font.BOLD, 14));
				adresseEcrit.setBounds(5, 123, 75, 26);
				frame.getContentPane().add(adresseEcrit);
				barreAdresse = new JFormattedTextField();
				barreAdresse.setBounds(84, 127, 133,20);
				barreAdresse.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent appuyerTouche) {
						if(barreAdresse.getText().length() >= 70) {
							appuyerTouche.consume();
						}
					}
				});
				frame.getContentPane().add(barreAdresse);

				// Saisie du sexe
				//Homme
				choixSexeEcrit = new JLabel("Sexe :");
				choixSexeEcrit.setFont(new Font("Arial", Font.BOLD, 14));
				choixSexeEcrit.setBounds(5, 154, 75, 26);
				frame.getContentPane().add(choixSexeEcrit);
				choixSexeHomme = new JRadioButton("Homme");
				choixSexeHomme.setBounds(74, 154, 78, 23);
				frame.getContentPane().add(choixSexeHomme);
				//Femme
				choixSexeFemme = new JRadioButton("Femme");
				choixSexeFemme.setBounds(154, 154, 109, 23);
				frame.getContentPane().add(choixSexeFemme);
				choixSexe = new ButtonGroup();
				choixSexe.add(choixSexeFemme);
				choixSexe.add(choixSexeHomme);

				// Saisie du type de compte
				choixTypeCompteEcrit = new JLabel("Compte :");
				choixTypeCompteEcrit.setFont(new Font("Arial", Font.BOLD, 14));
				choixTypeCompteEcrit.setBounds(5, 185, 75, 26);
				frame.getContentPane().add(choixTypeCompteEcrit);
				choixTypeCompteCourant = new JRadioButton("Courant");
				choixTypeCompteCourant.setBounds(74, 185, 78, 23);
				frame.getContentPane().add(choixTypeCompteCourant);
				choixTypeCompteJoint = new JRadioButton("Joint");
				choixTypeCompteJoint.setBounds(154, 185, 78, 23);
				frame.getContentPane().add(choixTypeCompteJoint);
				choixTypeCompteEpargne = new JRadioButton("Épargne");
				choixTypeCompteEpargne.setBounds(234, 185, 78, 23);
				frame.getContentPane().add(choixTypeCompteEpargne);
				choixTypeCompte = new ButtonGroup();
				choixTypeCompte.add(choixTypeCompteCourant);
				choixTypeCompte.add(choixTypeCompteJoint);
				choixTypeCompte.add(choixTypeCompteEpargne);

				// Supprimer un compte
				boutonSupprimerCompte = new JButton("Supprimer un compte ");
				boutonSupprimerCompte.setBounds(20, 92, 244, 37);
				frame.getContentPane().add(boutonSupprimerCompte);

				// Saisie du compte à supprimer
				numeroCompteASupprimerEcrit = new JLabel("Entrez le numéro du compte à supprimer :");
				numeroCompteASupprimerEcrit.setFont(new Font("Arial", Font.BOLD, 14));
				numeroCompteASupprimerEcrit.setBounds(40, 70, 300, 17);
				frame.getContentPane().add(numeroCompteASupprimerEcrit); 
				barreNumeroCompte = new JFormattedTextField();
				barreNumeroCompte.setBounds(125, 120, 133, 20);   
				barreNumeroCompte.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent appuyerTouche) {
						char caractere = appuyerTouche.getKeyChar();
						if(!(Character.isDigit(caractere)) || (caractere == KeyEvent.VK_BACK_SPACE) || (caractere == KeyEvent.VK_DELETE) || barreNumeroCompte.getText().length() >= 3 ){
							appuyerTouche.consume();
						}
					}
				});
				frame.getContentPane().add(barreNumeroCompte);

				// Créditer ou débiter un compte
				boutonAltererCompte = new JButton("Créditer/Débiter un compte");
				boutonAltererCompte.setBounds(20, 140, 244, 37);
				frame.getContentPane().add(boutonAltererCompte);

				// Saisie pour créditer ou débiter
				numeroCompteADebiterOuCrediterEcrit = new JLabel("Numéro du compte : ");
				numeroCompteADebiterOuCrediterEcrit.setBounds(5, 113, 150, 26);
				frame.getContentPane().add(numeroCompteADebiterOuCrediterEcrit);
				montantADebiterOuCrediterEcrit = new JLabel("Montant :");
				montantADebiterOuCrediterEcrit.setBounds(20, 40, 244, 37);
				frame.getContentPane().add(montantADebiterOuCrediterEcrit);
				barreMontantDebiterOuCrediter = new JFormattedTextField();
				barreMontantDebiterOuCrediter.setBounds(125, 50, 133,20);
				barreMontantDebiterOuCrediter.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent appuyerTouche) {
						char caractere = appuyerTouche.getKeyChar();
						if((caractere == KeyEvent.VK_MINUS)) {

						}
						else if(!(Character.isDigit(caractere)) || (caractere == KeyEvent.VK_BACK_SPACE) || (caractere == KeyEvent.VK_DELETE) ){
							appuyerTouche.consume();
						}
					}
				});
				frame.getContentPane().add(barreMontantDebiterOuCrediter);

				// Afficher compte

				// Afficher un compte
				boutonAfficherCompte = new JButton("Afficher un compte spécifique");
				boutonAfficherCompte.setBounds(20, 44, 244, 37);
				frame.getContentPane().add(boutonAfficherCompte);

				// Fenetre de l'affichage du ou des comptes
				tableauCompte = new JTable(tableau);
				tableauCompte.setBounds(10, 22, 414, 160);
				tableauCompte.setPreferredScrollableViewportSize(new Dimension(500,50));
				barreDeDefilement = new JScrollPane(tableauCompte,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				barreDeDefilement.setBounds(10, 50, 1000, 160);
				frame.getContentPane().add(barreDeDefilement);
				CompteSelectionneEcrit = new JLabel("Compte sélectionné :");
				CompteSelectionneEcrit.setBounds(400, 10, 244, 37);				
				frame.getContentPane().add(CompteSelectionneEcrit);
				barreRechercheCompte = new JFormattedTextField();
				barreRechercheCompte.setBounds(525, 20, 24, 20);
				barreRechercheCompte.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent appuyerTouche) {
						char caractere = appuyerTouche.getKeyChar();
						if(!(Character.isDigit(caractere)) || (caractere == KeyEvent.VK_BACK_SPACE) || (caractere == KeyEvent.VK_DELETE) || (barreRechercheCompte.getText().length() >= 3) ){
							appuyerTouche.consume();
						}
					}
				});
				frame.getContentPane().add(barreRechercheCompte);

				// Bouton afficher tout les comptes
				boutonAfficherToutComptes = new JButton("Afficher tous les comptes");
				boutonAfficherToutComptes.setBounds(20, 92, 244, 37);
				frame.getContentPane().add(boutonAfficherToutComptes);

				// Afficher tout les comptes
				ToutCompteEcrit = new JLabel("Tout les comptes");
				ToutCompteEcrit.setBounds(400, 10, 244, 37);
				frame.getContentPane().add(ToutCompteEcrit);
				sommeSoldeEcrit = new JLabel("Somme des soldes : ");
				sommeSoldeEcrit.setBounds(700, 10, 244, 37);
				frame.getContentPane().add(sommeSoldeEcrit);

				// Bouton afficher tout les comptes d'un type
				boutonAfficherToutComptesType = new JButton("Afficher tous les compte par type");
				boutonAfficherToutComptesType.setBounds(20, 140, 244, 37);
				frame.getContentPane().add(boutonAfficherToutComptesType);

				// Afficher tout les comptes d'un type
				ToutTypeCompteEcrit = new JLabel("Type de compte :");
				ToutTypeCompteEcrit.setBounds(400, 10, 244, 37);
				frame.getContentPane().add(ToutTypeCompteEcrit);

				// Vérifier carte

				// Bouton vérifier une carte
				boutonVerifierCarte = new JButton("Vérifier un numéro de carte bancaire");
				boutonVerifierCarte.setBounds(20, 44, 244, 37);
				frame.getContentPane().add(boutonVerifierCarte);

				// Saisie pour vérifier une carte
				carteAVerifierEcrit = new JLabel("Entrer le numéro de la carte à vérifier :");
				carteAVerifierEcrit.setFont(new Font("Arial", Font.BOLD, 14));
				carteAVerifierEcrit.setBounds(40, 70, 300, 17);
				frame.getContentPane().add(carteAVerifierEcrit);
				barreVerifierCarte = new JFormattedTextField();
				barreVerifierCarte.setBounds(125, 120, 133, 20);
				barreVerifierCarte.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent appuyerTouche) {
						char caractere = appuyerTouche.getKeyChar();
						if(!(Character.isDigit(caractere)) || (caractere == KeyEvent.VK_BACK_SPACE) || (caractere == KeyEvent.VK_DELETE) || barreVerifierCarte.getText().length() >= 16 ){
							appuyerTouche.consume();
						}
					}
				});
				frame.getContentPane().add(barreVerifierCarte);

				// Interface aide
				aideEcrit= new JLabel("<html>Aide :<br/>"
						+ "Les saisies de date de naissances ont pour format :<br/> JJ/MM/YYYY"
						+ "<br/><br/> Les numéros de carte bancaire ont 16 chiffres.</html>",SwingConstants.CENTER);
				aideEcrit.setFont(new Font("Arial", Font.BOLD, 14));
				aideEcrit.setBounds(-50, -40,500, 200);
				frame.getContentPane().add(aideEcrit);
				
				// Autres éléments d'interface

				// Valider
				boutonValider = new JButton("Valider");
				boutonValider.setBounds(10, 213, 115, 37);
				frame.getContentPane().add(boutonValider);

				// Retour
				boutonRetour = new JButton("Retour");
				boutonRetour.setBounds(309, 111,115, 37);
				frame.getContentPane().add(boutonRetour);
				choixTypeCompteCourant.setBounds(74, 185, 78, 23);
				choixTypeCompteJoint.setBounds(154, 185, 78, 23);
				choixTypeCompteEpargne.setBounds(234, 185, 78, 23);

			}

			// Enlever l'interface
			void enleverInterface() {

				// Enlever interface crée compte
				boutonCreeCompte.setVisible(false);
				barreNom.setVisible(false);	
				barrePrenom.setVisible(false);
				barreDateNaissance.setVisible(false);
				barreAdresse.setVisible(false);
				nomEcrit.setVisible(false);
				prenomEcrit.setVisible(false);
				adresseEcrit.setVisible(false);
				dateNaissanceEcrit.setVisible(false);
				choixSexeFemme.setVisible(false);
				choixSexeHomme.setVisible(false);
				choixSexeEcrit.setVisible(false);
				choixTypeCompteEcrit.setVisible(false);
				choixTypeCompteCourant.setVisible(false);
				choixTypeCompteJoint.setVisible(false);
				choixTypeCompteEpargne.setVisible(false);

				// Enlever interface supprimer compte
				boutonSupprimerCompte.setVisible(false);
				barreNumeroCompte.setVisible(false);
				numeroCompteASupprimerEcrit.setVisible(false);

				// Enlever interface Afficher compte
				boutonAfficherCompte.setVisible(false);
				boutonAfficherToutComptes.setVisible(false);
				boutonAfficherToutComptesType.setVisible(false);

				// Enlever interface débiter ou supprimer compte 
				barreMontantDebiterOuCrediter.setVisible(false);
				numeroCompteADebiterOuCrediterEcrit.setVisible(false);
				montantADebiterOuCrediterEcrit.setVisible(false);
				boutonAltererCompte.setVisible(false);

				// Enlever interface Affichage compte
				barreDeDefilement.setVisible(false);
				barreRechercheCompte.setVisible(false);
				CompteSelectionneEcrit.setVisible(false);

				// Enlever interface Affichage tout compte
				ToutCompteEcrit.setVisible(false);
				sommeSoldeEcrit.setVisible(false);

				// Enlever interface Affichage tout compte type
				ToutTypeCompteEcrit.setVisible(false);

				// Enlever interface Vérifier carte
				carteAVerifierEcrit.setVisible(false);
				barreVerifierCarte.setVisible(false);
				boutonVerifierCarte.setVisible(false);
				
				// Enlever interface Aide
				aideEcrit.setVisible(false);

				// Enlever autres interface 
				boutonValider.setVisible(false);
				boutonRetour.setVisible(false);

				// Autre
				interfaceEcrit = "";
				boutonValider.setBounds(10, 213, 115, 37);

			}

			//Réinitialise la position des éléments
			void reinitialiserPositionBouton() {
				frame.setBounds(100, 100, 450, 300);
				choixTypeCompteCourant.setBounds(74, 185, 78, 23);
				choixTypeCompteJoint.setBounds(154, 185, 78, 23);
				choixTypeCompteEpargne.setBounds(234, 185, 78, 23);
				boutonRetour.setBounds(309, 111,115, 37);
			}

			// Afficher l'interface de gestion de comtes
			void afficherInterfaceGestionCompte() {
				boutonCreeCompte.setVisible(true);
				boutonSupprimerCompte.setVisible(true);
				boutonAltererCompte.setVisible(true);
			}

			// Afficher l'interface de création de compte
			void afficherInterfaceCreeCompte() {
				interfaceEcrit = "Créer un compte";
				barreNom.setVisible(true);
				barrePrenom.setVisible(true);
				barreDateNaissance.setVisible(true);
				barreAdresse.setVisible(true);
				choixSexeFemme.setVisible(true);
				choixSexeHomme.setVisible(true);
				choixTypeCompteCourant.setVisible(true);
				choixTypeCompteJoint.setVisible(true);
				choixTypeCompteEpargne.setVisible(true);

				nomEcrit.setVisible(true);
				prenomEcrit.setVisible(true);
				adresseEcrit.setVisible(true);
				dateNaissanceEcrit.setVisible(true);
				choixSexeEcrit.setVisible(true);
				choixTypeCompteEcrit.setVisible(true);
				boutonValider.setVisible(true);
				boutonRetour.setVisible(true);

			}

			void afficherInterfaceSupprimerCompte() { 
				interfaceEcrit ="Supprimer un compte";
				barreNumeroCompte.setVisible(true);
				numeroCompteASupprimerEcrit.setVisible(true);
				boutonValider.setVisible(true);	
				boutonRetour.setVisible(true);

			}

			void afficherInterfaceDebiterOuCrediterCompte() {
				interfaceEcrit ="Débiter ou créditer un compte";
				boutonValider.setVisible(true);	
				boutonRetour.setVisible(true);
				barreNumeroCompte.setVisible(true);
				barreMontantDebiterOuCrediter.setVisible(true);
				numeroCompteADebiterOuCrediterEcrit.setVisible(true);
				montantADebiterOuCrediterEcrit.setVisible(true);
			}

			// Afficher l'interface d'affichage des comptes
			void afficherInterfaceAfficherCompte() { 
				boutonAfficherCompte.setVisible(true);
				boutonAfficherToutComptes.setVisible(true);
				boutonAfficherToutComptesType.setVisible(true);
			}

			// Changer les dimensions pour les tableaux
			void changerDimensionElements() {
				frame.setBounds(100, 100, 1200, 300);
				boutonRetour.setBounds(1050, 111,115, 37);
			}

			// Afficher l'interface d'affichage d'un seul compte
			void afficherInterfaceTableauCompte(){
				interfaceEcrit = "Afficher un compte spécifique";
				boutonValider.setVisible(true);
				boutonValider.setBounds(560, 17, 75, 25);
				barreDeDefilement.setVisible(true);
				boutonRetour.setVisible(true);
				barreRechercheCompte.setVisible(true);
				CompteSelectionneEcrit.setVisible(true);
			}

			// Afficher l'interface d'affichage de tout les comptes
			void afficherInterfaceTableauToutCompte(){
				interfaceEcrit = "Afficher interface tout compte";
				sommeSoldeEcrit.setVisible(true);
				barreDeDefilement.setVisible(true);
				boutonRetour.setVisible(true);
				ToutCompteEcrit.setVisible(true);
			}

			// Afficher l'interface d'affichage des comptes par type
			void afficherInterfaceTableauToutCompteType(){
				interfaceEcrit = "Afficher interface type compte";
				barreDeDefilement.setVisible(true);
				boutonRetour.setVisible(true);
				ToutTypeCompteEcrit.setVisible(true);
				choixTypeCompteCourant.setVisible(true);
				choixTypeCompteJoint.setVisible(true);
				choixTypeCompteEpargne.setVisible(true);
				choixTypeCompteCourant.setBounds(515, 10, 75, 37);
				choixTypeCompteJoint.setBounds(600, 10, 75, 37);
				choixTypeCompteEpargne.setBounds(675, 10, 244, 37);
			}

			// Afficher l'interface de vérification de carte bancaire
			void afficherInterfaceVerifierCarte() {
				interfaceEcrit = "Vérifier une carte bancaire";
				boutonRetour.setVisible(true);
				boutonValider.setVisible(true);
				carteAVerifierEcrit.setVisible(true);
			}

		}

		Interface interfaceAppCompte = new Interface();
		interfaceAppCompte.tableau.setColumnIdentifiers(interfaceAppCompte.titre);

		class CompteBancaires{

			// Attributs
			String prenom;
			String nom;
			String dateDeNaissance;
			String sexe;
			String adresse;
			String statue;
			String solde;
			private long numeroDeCompte;
			private String typeDeCompte;
			File fichierNumeroCompte;
			File fichierCompteBancaires;
			File fichierCompteCourant;
			File fichierCompteJoint;
			File fichierCompteEpargne;
			File fichierHistoriqueDesTransactions;

			// Méthodes
			void creerCompte() throws IOException{
				this.nom = interfaceAppCompte.barreNom.getText();
				this.prenom = interfaceAppCompte.barrePrenom.getText();
				this.dateDeNaissance = interfaceAppCompte.barreDateNaissance.getText();
				this.sexe = "";
				if(interfaceAppCompte.choixSexeHomme.isSelected()) {
					this.sexe = "Homme";
				}
				if(interfaceAppCompte.choixSexeFemme.isSelected()) {
					this.sexe = "Femme";
				}
				this.adresse = interfaceAppCompte.barreAdresse.getText();
				if(interfaceAppCompte.choixTypeCompteCourant.isSelected()) {
					this.typeDeCompte ="Courant";
				}

				if(interfaceAppCompte.choixTypeCompteJoint.isSelected()) {
					this.typeDeCompte = "Joint";
				}

				if(interfaceAppCompte.choixTypeCompteEpargne.isSelected()) {
					this.typeDeCompte = "Epargne";
				}

				fichierNumeroCompte = new File("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/NumeroCompte.txt");

				FileReader lireDansFichierNumeroCompte = new FileReader(fichierNumeroCompte);
				BufferedReader ouvreFluxLireDansFichierNumeroCompte =  new BufferedReader(lireDansFichierNumeroCompte); 
				String ligneActuel = "";
				String derniereLigne = "";

				while ((ligneActuel = ouvreFluxLireDansFichierNumeroCompte.readLine()) != null){ // La dernière ligne du fichier NumeroCompte
					derniereLigne = ligneActuel;                                                 // correspond au dernier numéro compte crée
				}
				this.numeroDeCompte = (Long.parseLong(derniereLigne) + 1);                       // on crée un nouveau numéro compte en rajoutant 1 au dernier numéro compte
				ouvreFluxLireDansFichierNumeroCompte.close();
				lireDansFichierNumeroCompte.close();

				FileWriter ecrireDansFichierNumeroCompte = new FileWriter(fichierNumeroCompte);
				BufferedWriter ouvreFluxEcrireDansFichierNumeroCompte = new BufferedWriter(ecrireDansFichierNumeroCompte);
				ouvreFluxEcrireDansFichierNumeroCompte.append(Long.toString(this.getNumeroDeCompte()));  // Et on rajoute le noveau numéro compte crée au fichier
				ouvreFluxEcrireDansFichierNumeroCompte.close();
				ecrireDansFichierNumeroCompte.close();
				this.solde = Long.toString(this.numeroDeCompte) + "solde0";               // solde et statue sont initialisé à la fin car pour identifier le compte auquel ils sont
				this.statue = Long.toString(this.getNumeroDeCompte()) + "actif";          // liés on utilise le numéro compte
			}

			void sauvegarderDansFichier(String addresseDuFichier) throws IOException {
				fichierCompteBancaires = new File(addresseDuFichier);
				FileWriter ecrireDansFichierCompteBancaire = new FileWriter(fichierCompteBancaires,true);
				BufferedWriter ouvreFluxEcrireDansFichierCompteBancaire = new BufferedWriter(ecrireDansFichierCompteBancaire);
				ouvreFluxEcrireDansFichierCompteBancaire.newLine();                            // ligne utilisé pour séparer les donnés de compte différent
				ouvreFluxEcrireDansFichierCompteBancaire.write(Long.toString(this.getNumeroDeCompte()));
				ouvreFluxEcrireDansFichierCompteBancaire.newLine();
				ouvreFluxEcrireDansFichierCompteBancaire.write(this.nom);
				ouvreFluxEcrireDansFichierCompteBancaire.newLine();
				ouvreFluxEcrireDansFichierCompteBancaire.write(this.prenom);
				ouvreFluxEcrireDansFichierCompteBancaire.newLine();
				ouvreFluxEcrireDansFichierCompteBancaire.write(this.dateDeNaissance);
				ouvreFluxEcrireDansFichierCompteBancaire.newLine();
				ouvreFluxEcrireDansFichierCompteBancaire.write(this.sexe);
				ouvreFluxEcrireDansFichierCompteBancaire.newLine();
				ouvreFluxEcrireDansFichierCompteBancaire.write(this.adresse);
				ouvreFluxEcrireDansFichierCompteBancaire.newLine();
				ouvreFluxEcrireDansFichierCompteBancaire.write(this.getTypeDeCompte());
				ouvreFluxEcrireDansFichierCompteBancaire.newLine();
				ouvreFluxEcrireDansFichierCompteBancaire.write(this.solde);
				ouvreFluxEcrireDansFichierCompteBancaire.newLine();
				ouvreFluxEcrireDansFichierCompteBancaire.write(this.statue);
				ouvreFluxEcrireDansFichierCompteBancaire.newLine();

				ouvreFluxEcrireDansFichierCompteBancaire.close();
				ecrireDansFichierCompteBancaire.close();
			}

			void afficherCompte() {                 // procédure inutile pour la fenetre de gestion de compte bancaire, elle ne sert que pour la vérification lors de l'altération du programme
				System.out.println("numéro de compte = " + this.getNumeroDeCompte());
				System.out.println("nom = " + this.nom);
				System.out.println("Prénom = " + this.prenom);
				System.out.println("née le = " + this.dateDeNaissance);
				System.out.println("sexe = " + this.sexe);
				System.out.println("adresse = " + this.adresse);
				System.out.println("type de compte = " + this.getTypeDeCompte());
				System.out.println("solde de compte = " + this.solde);
				System.out.println("statue du compte = " + this.statue);
			}

			void supprimerCompte(String adresseDuFichier) throws IOException {
				fichierCompteBancaires = new File(adresseDuFichier);
				File fichierDeTransition = new File("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/fichierDeTransition.txt");
				FileWriter ecrireDansFichierDeTransition = new FileWriter(fichierDeTransition);
				BufferedWriter ouvreFluxEcrireDansFichierDeTransition = new BufferedWriter(ecrireDansFichierDeTransition);
				FileReader lireDansFichierCompteBancaires = new FileReader(fichierCompteBancaires);
				BufferedReader ouvreFluxLireDansFichierCompteBancaires = new BufferedReader(lireDansFichierCompteBancaires);

				String ligneActuel = "";

				while ((ligneActuel = ouvreFluxLireDansFichierCompteBancaires.readLine()) != null){
					if(ligneActuel.equals(interfaceAppCompte.barreNumeroCompte.getText() + "actif")) {              // la chaine numéroDeCompte + actif/supprimé correspond
						ligneActuel = interfaceAppCompte.barreNumeroCompte.getText() + "supprimé";                  // permet de savoir si le compte auquel réfère le numéro compte
					}                                                                                               // est supprimé ou pas, pour supprimé on change donc juste une partie de la chaine
					ouvreFluxEcrireDansFichierDeTransition.write(ligneActuel);                                      // numéroDeCompte + actif/supprimé
					ouvreFluxEcrireDansFichierDeTransition.newLine();			
				} 

				lireDansFichierCompteBancaires.close();
				ouvreFluxLireDansFichierCompteBancaires.close();
				ouvreFluxEcrireDansFichierDeTransition.close();
				ecrireDansFichierDeTransition.close();
				                                                                                                    // Pour changer une donné d'un fichier on recopie ce fichier sur un autre, le fichier
				FileWriter ecrireDansFichierCompteBancaires = new FileWriter(fichierCompteBancaires);               // de transition. Tout en modifiant les données voulu, puis on recopie le tout sur le fichier de départ
				BufferedWriter ouvreFluxEcrireDansFichierCompteBancaires = new BufferedWriter(ecrireDansFichierCompteBancaires);
				FileReader lireDansFichierDeTransition = new FileReader(fichierDeTransition);
				BufferedReader ouvreFluxLireDansFichierDeTransition = new BufferedReader(lireDansFichierDeTransition);

				ligneActuel = "";

				while ((ligneActuel = ouvreFluxLireDansFichierDeTransition.readLine()) != null){
					ouvreFluxEcrireDansFichierCompteBancaires.write(ligneActuel);
					ouvreFluxEcrireDansFichierCompteBancaires.newLine();			
				} 

				lireDansFichierDeTransition.close();
				ouvreFluxLireDansFichierDeTransition.close();
				ouvreFluxEcrireDansFichierCompteBancaires.close();
				ecrireDansFichierCompteBancaires.close();
			}

			void sauvegardeHistoriqueTransaction(String ligneActuel) throws IOException {
				fichierHistoriqueDesTransactions = new File("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/historiqueDesTransactions.txt");
				FileWriter ecrireDansFichierHistoriqueDesTransactions = new FileWriter(fichierHistoriqueDesTransactions,true);
				BufferedWriter ouvreFluxEcrireDansFichierHistoriqueDesTransactions = new BufferedWriter(ecrireDansFichierHistoriqueDesTransactions);
				SimpleDateFormat formatDeLaDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date dateDeLaTransaction = new Date();

				ouvreFluxEcrireDansFichierHistoriqueDesTransactions.write("Compte numéro : " + ligneActuel.substring(0, 3));
				ouvreFluxEcrireDansFichierHistoriqueDesTransactions.newLine();
				ouvreFluxEcrireDansFichierHistoriqueDesTransactions.write("Solde après transaction : " + ligneActuel.substring(8,ligneActuel.length()) + "$");
				ouvreFluxEcrireDansFichierHistoriqueDesTransactions.newLine();
				ouvreFluxEcrireDansFichierHistoriqueDesTransactions.write("Transaction effectué (dd/MM/yyyy HH:mm:ss): " + formatDeLaDate.format(dateDeLaTransaction));
				ouvreFluxEcrireDansFichierHistoriqueDesTransactions.newLine();
				ouvreFluxEcrireDansFichierHistoriqueDesTransactions.newLine();


				ouvreFluxEcrireDansFichierHistoriqueDesTransactions.close();
				ecrireDansFichierHistoriqueDesTransactions.close();
			}

			void altererUnCompte(String adresseDuFichier) throws IOException {      // Ici on utilise le même principe que pour la suppression de compte,
				int transaction = 0;                                                // en utilisant cette fois-ci le mots clé solde.
				String ligneActuel = "";


				if(!interfaceAppCompte.barreMontantDebiterOuCrediter.getText().contentEquals("")) {

					transaction = Integer.parseInt(interfaceAppCompte.barreMontantDebiterOuCrediter.getText());

					fichierCompteBancaires = new File(adresseDuFichier);
					File fichierDeTransition = new File("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/fichierDeTransition.txt");
					FileWriter ecrireDansFichierDeTransition = new FileWriter(fichierDeTransition);
					BufferedWriter ouvreFluxEcrireDansFichierDeTransition = new BufferedWriter(ecrireDansFichierDeTransition);
					FileReader lireDansFichierCompteBancaires = new FileReader(fichierCompteBancaires);
					BufferedReader ouvreFluxLireDansFichierCompteBancaires = new BufferedReader(lireDansFichierCompteBancaires);

					while ((ligneActuel = ouvreFluxLireDansFichierCompteBancaires.readLine()) != null){
						if(ligneActuel.length() > 8) {
							if(ligneActuel.substring(0,8).equals(interfaceAppCompte.barreNumeroCompte.getText() + "solde")) {
								ligneActuel = interfaceAppCompte.barreNumeroCompte.getText() + "solde" + Integer.toString(Integer.parseInt(ligneActuel.substring(8,ligneActuel.length())) + transaction);
								sauvegardeHistoriqueTransaction(ligneActuel);
							}
						}
						ouvreFluxEcrireDansFichierDeTransition.write(ligneActuel);
						ouvreFluxEcrireDansFichierDeTransition.newLine();			
					} 

					lireDansFichierCompteBancaires.close();
					ouvreFluxLireDansFichierCompteBancaires.close();
					ouvreFluxEcrireDansFichierDeTransition.close();
					ecrireDansFichierDeTransition.close();

					FileWriter ecrireDansFichierCompteBancaires = new FileWriter(fichierCompteBancaires);
					BufferedWriter ouvreFluxEcrireDansFichierCompteBancaires = new BufferedWriter(ecrireDansFichierCompteBancaires);
					FileReader lireDansFichierDeTransition = new FileReader(fichierDeTransition);
					BufferedReader ouvreFluxLireDansFichierDeTransition = new BufferedReader(lireDansFichierDeTransition);

					ligneActuel = "";

					while ((ligneActuel = ouvreFluxLireDansFichierDeTransition.readLine()) != null){
						ouvreFluxEcrireDansFichierCompteBancaires.write(ligneActuel);
						ouvreFluxEcrireDansFichierCompteBancaires.newLine();			
					} 

					lireDansFichierDeTransition.close();
					ouvreFluxLireDansFichierDeTransition.close();
					ouvreFluxEcrireDansFichierCompteBancaires.close();
					ecrireDansFichierCompteBancaires.close();
				}

			}

			public long getNumeroDeCompte() {              // Ces méthodes sont utilisé pour avoir accès aux attributs privés de la classe grand-mère
				return numeroDeCompte;                      // pout les manipuler ou les modifier
			}

			public String getTypeDeCompte() {
				return typeDeCompte;
			}

			public void setNumeroDeCompte(long numeroDeCompte) {   
				this.numeroDeCompte = numeroDeCompte;
			}

			public void setTypeDeCompte(String typeDeCompte) {
				this.typeDeCompte = typeDeCompte;
			}

			void verifierCarte() {
				String numeroDeCarte;
				numeroDeCarte = "";
				int cleDeLuhn;
				int somme = 0;
				String chiffresDePlaceImpaire = new String("");
				int i;

				numeroDeCarte = interfaceAppCompte.barreVerifierCarte.getText();

				if (numeroDeCarte.length() != 16) {
					JOptionPane.showMessageDialog(null,"Saisie non valide pour un  numéro de carte bancaire"); 
					interfaceAppCompte.barreVerifierCarte.setText("");
					numeroDeCarte = interfaceAppCompte.barreVerifierCarte.getText();
				}
				else {
					cleDeLuhn = Integer.parseInt(numeroDeCarte.substring(numeroDeCarte.length() - 1, numeroDeCarte.length()));

					for(i = 0; i < numeroDeCarte.length(); i++) {
						if(i % 2 ==0) {                                                    // On prend les chiffres de place impaire 
							chiffresDePlaceImpaire += numeroDeCarte.substring(i, i + 1);
						}
						else {
							somme += Integer.parseInt(numeroDeCarte.substring(i, i + 1));  // On commence déjà à calculer la somme
						}
					}

					int[] tableauChiffresDePlaceImpaire = {0,0,0,0,0,0,0,0};

					for(i = 0; i < 8; i++) {
						tableauChiffresDePlaceImpaire[i] = Character.getNumericValue(chiffresDePlaceImpaire.charAt(i)); // On les mets dans un tableau
					}                                                                                                   // pour traiter plus facilement

					for(i = 0; i < 8; i++) {
						tableauChiffresDePlaceImpaire[i] = tableauChiffresDePlaceImpaire[i] * 2;		 // on les mutiplie par 2 
						if(tableauChiffresDePlaceImpaire[i] > 9) {
							tableauChiffresDePlaceImpaire[i] = tableauChiffresDePlaceImpaire[i] - 9; // on enlève 9 elles sont  > 9
						}
					}

					for(i = 0; i < 8; i++) {
						somme += tableauChiffresDePlaceImpaire[i];                                    // On fait la somme des chiffres de place impaire 
					}                                                                                 // multiplié par 2

					// Affichage des résultats
					if((somme % 10) == 0) {                                                        
						JOptionPane.showMessageDialog(null,"La clée de Luhn " + cleDeLuhn +" est valide.\n"
								+ "Car le reste vaux " + (somme % 10)
								+ "\nCe numéro de carte est donc valide"); 
					}
					else {
						JOptionPane.showMessageDialog(null,"La clée de Luhn n'est pas valide,\n"
								+ "Ce numéro de carte n'est donc pas valide"); 
					}
				}
			}
		}


		class CompteCourant extends CompteBancaires{

			// Attributs
			long identifiantTitulaire;
			int sommeSolde;
			String gestionException = new String();


			// Méthodes
			void creerCompte() {
				try {
					super.creerCompte();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Problème lors de la création du compte");
				}
				int i = 0;
				char caractere = ' ';
				String identifiantEnChaine = "";
				try {
					if(!(this.nom.length() < 2 || this.prenom.length() < 2 || this.sexe.equals("") || this.dateDeNaissance.length()!= 10  || super.adresse.length() < 2 || super.typeDeCompte.equals(""))) {
						if(this.dateDeNaissance.substring(2, 3).equals("/") && this.dateDeNaissance.substring(5, 6).equals("/")
								&& (Integer.parseInt(dateDeNaissance.substring(0, 2)) <= 31 && Integer.parseInt(dateDeNaissance.substring(0, 2)) != 0)
								&& (Integer.parseInt(dateDeNaissance.substring(3, 5)) <= 12) && (Integer.parseInt(dateDeNaissance.substring(3, 5)) != 0)
								&& (Integer.parseInt(dateDeNaissance.substring(6, 10)) >= 1871) && (Integer.parseInt(dateDeNaissance.substring(6, 10)) <= 2021)){
							for(i = 0; i <(this.nom.subSequence(0, 2).length()); i++) {
								caractere = this.nom.subSequence(0, 2).charAt(i);
								identifiantEnChaine += Integer.toString(caractere);                    
							}
							for(i = 0; i <(this.prenom.subSequence(0, 1).length()); i++) {
								caractere = this.prenom.subSequence(0, 1).charAt(i);
								identifiantEnChaine += Integer.toString(caractere);
							}
							this.identifiantTitulaire = Integer.parseInt(identifiantEnChaine);   // l'identifiant titulaire est crée à partir du nom et prénom du titulaire
							gestionException = "Saisie non vide";
						}
						else {
							JOptionPane.showMessageDialog(null,"Le format de la date de naissance est invalide, il faut DD/MM/YYYY");
							this.gestionException = "Saisie vide";
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"Il manque des informations ou alors les informations sont invraissemblable"); 
						this.gestionException = "Saisie vide";
					}
				}
				catch(StringIndexOutOfBoundsException erreurSaisie) {
					JOptionPane.showMessageDialog(null, "Les informations saisies semblent invraissemblable\nCréation du compte MAIS identifiant titulaire mit à 0");
				}
				catch(NumberFormatException erreurConversionNombre) {
					JOptionPane.showMessageDialog(null, "Une erreur est survenue, la date n'est pas valide");
				}
			}
			void sauvegarderDansFichier(String nomDuFichier) throws IOException {
				if(!(this.gestionException.equals("Saisie vide"))) {

					super.sauvegarderDansFichier(nomDuFichier);
					fichierCompteCourant = new File(nomDuFichier);
					FileWriter ecrireDansFichierCompteCourant = new FileWriter(fichierCompteCourant,true);
					BufferedWriter ouvreFluxEcrireDansFichierCompteCourant = new BufferedWriter(ecrireDansFichierCompteCourant);
					ouvreFluxEcrireDansFichierCompteCourant.write(Long.toString(this.identifiantTitulaire));
					ouvreFluxEcrireDansFichierCompteCourant.newLine();
					ouvreFluxEcrireDansFichierCompteCourant.close();
					ecrireDansFichierCompteCourant.close();
				}
				else {

				}
			}
			void afficherCompte() {
				super.afficherCompte();
				System.out.println("Identifiant titulaire" + this.identifiantTitulaire);
			}

			void recupererCompte(String adresseDuFichier) throws IOException{
				fichierCompteCourant = new File(adresseDuFichier);
				FileReader lireDansFichierCompteCourant = new FileReader(fichierCompteCourant);
				BufferedReader ouvreFluxLireDansFichierCompteCourant = new BufferedReader(lireDansFichierCompteCourant);
				try {
					while ((ouvreFluxLireDansFichierCompteCourant.readLine()) != null){
						super.numeroDeCompte = Integer.parseInt(ouvreFluxLireDansFichierCompteCourant.readLine());
						this.nom = ouvreFluxLireDansFichierCompteCourant.readLine();
						this.prenom = ouvreFluxLireDansFichierCompteCourant.readLine();
						this.dateDeNaissance = ouvreFluxLireDansFichierCompteCourant.readLine();
						this.sexe = ouvreFluxLireDansFichierCompteCourant.readLine();
						this.adresse = ouvreFluxLireDansFichierCompteCourant.readLine();
						super.typeDeCompte = ouvreFluxLireDansFichierCompteCourant.readLine();
						this.solde = ouvreFluxLireDansFichierCompteCourant.readLine();
						this.statue = ouvreFluxLireDansFichierCompteCourant.readLine();
						this.identifiantTitulaire = Integer.parseInt(ouvreFluxLireDansFichierCompteCourant.readLine());
						if(this.statue.substring(3, 8).equals("actif")) {                    
							sommeSolde += Integer.parseInt(this.solde.substring(8, this.solde.length()));
							Object[] donneesCompteCourant = {super.getNumeroDeCompte(),this.identifiantTitulaire,this.nom,this.prenom,this.dateDeNaissance,this.sexe,this.adresse,super.getTypeDeCompte(),null,this.solde.substring(8,this.solde.length()),null};
							if(!interfaceAppCompte.interfaceEcrit.equals("Afficher un compte spécifique")) {
								interfaceAppCompte.tableau.addRow(donneesCompteCourant);
							}
							else {
								if(super.numeroDeCompte == Integer.parseInt(interfaceAppCompte.barreRechercheCompte.getText())) {
									interfaceAppCompte.tableau.addRow(donneesCompteCourant);
								}
							}
						}
					} 
				}
				catch(NumberFormatException erreurAffichage) {
					JOptionPane.showMessageDialog(null, "Il y a eu un problème d'affichage");
				}
				lireDansFichierCompteCourant.close();
				ouvreFluxLireDansFichierCompteCourant.close();
			}


		}

		class CompteJoint extends CompteCourant{

			// Attributs
			private long identifiantTitulaireJoint;
			String identifiantTitulaireJointEnChaine = new String("");

			// Méthodes
			void creerCompte() {
				super.creerCompte();
				if(!(this.gestionException.equals("Saisie vide"))) {
					this.identifiantTitulaireJointEnChaine = JOptionPane.showInputDialog(null,"Entrez l'indentifiant du titulaire qui partage le compte :");
					try {
						if(!this.identifiantTitulaireJointEnChaine.equals("")) {
							try {
								this.identifiantTitulaireJoint = Long.parseLong(identifiantTitulaireJointEnChaine);
							}
							catch(NumberFormatException exceptionPasUnNombre) {
								JOptionPane.showMessageDialog(null, "Il y a eu une mauvaise saisie,L'identifiant joint vaut donc l'identifiant titulaire");
								this.identifiantTitulaireJoint = this.identifiantTitulaire;
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "L'identifiant joint vaut l'identifiant titulaire en l'absence de saisie");
							this.identifiantTitulaireJoint = this.identifiantTitulaire;
						}
					}
					catch(NullPointerException erreurDePointage) {
						JOptionPane.showMessageDialog(null, "Le compte a quand même été crée, l'identifiant joint vaut l'identifiant titulaire");
						this.identifiantTitulaireJoint = this.identifiantTitulaire;
					}
				}

				else {

				}
			}

			void sauvegarderDansFichier(String nomDuFichier) throws IOException {
				if(!(this.gestionException.equals("Saisie vide"))) {
					super.sauvegarderDansFichier(nomDuFichier);
					fichierCompteJoint = new File(nomDuFichier);
					FileWriter ecrireDansFichierCompteJoint = new FileWriter(fichierCompteJoint,true);
					BufferedWriter ouvreFluxEcrireDansFichierCompteJoint = new BufferedWriter(ecrireDansFichierCompteJoint);
					ouvreFluxEcrireDansFichierCompteJoint.write(Long.toString(this.identifiantTitulaireJoint));
					ouvreFluxEcrireDansFichierCompteJoint.newLine();
					ouvreFluxEcrireDansFichierCompteJoint.close();
					ecrireDansFichierCompteJoint.close();
				}
				else {

				}
			}

			void afficherCompte() {
				super.afficherCompte();
				System.out.println("Compte Joint : " + this.identifiantTitulaireJoint);
			}

			void recupererCompte(String adresseDuFichier) throws IOException{
				fichierCompteJoint = new File(adresseDuFichier);
				FileReader lireDansFichierCompteJoint = new FileReader(fichierCompteJoint);
				BufferedReader ouvreFluxLireDansFichierCompteJoint = new BufferedReader(lireDansFichierCompteJoint);
				try {
					while ((ouvreFluxLireDansFichierCompteJoint.readLine()) != null){
						setNumeroDeCompte (Integer.parseInt(ouvreFluxLireDansFichierCompteJoint.readLine()));
						this.nom = ouvreFluxLireDansFichierCompteJoint.readLine();
						this.prenom = ouvreFluxLireDansFichierCompteJoint.readLine();
						this.dateDeNaissance = ouvreFluxLireDansFichierCompteJoint.readLine();
						this.sexe = ouvreFluxLireDansFichierCompteJoint.readLine();
						this.adresse = ouvreFluxLireDansFichierCompteJoint.readLine();
						setTypeDeCompte(ouvreFluxLireDansFichierCompteJoint.readLine());
						this.solde = ouvreFluxLireDansFichierCompteJoint.readLine();
						this.statue = ouvreFluxLireDansFichierCompteJoint.readLine();
						this.identifiantTitulaire = Integer.parseInt(ouvreFluxLireDansFichierCompteJoint.readLine());
						this.identifiantTitulaireJoint= Integer.parseInt(ouvreFluxLireDansFichierCompteJoint.readLine());
						if(this.statue.substring(3, this.statue.length()).equals("actif")) {
							sommeSolde += Integer.parseInt(this.solde.substring(8, this.solde.length()));
							Object[] donneesCompteJoint = {getNumeroDeCompte(),this.identifiantTitulaire,this.nom,this.prenom,this.dateDeNaissance,this.sexe,this.adresse,getTypeDeCompte(),this.identifiantTitulaireJoint,this.solde.substring(8,this.solde.length()),null};
							if(!interfaceAppCompte.interfaceEcrit.equals("Afficher un compte spécifique")) {
								interfaceAppCompte.tableau.addRow(donneesCompteJoint);
							}
							else {
								if(getNumeroDeCompte() == Integer.parseInt(interfaceAppCompte.barreRechercheCompte.getText())){
									interfaceAppCompte.tableau.addRow(donneesCompteJoint);
								}
							}
						}
					} 
				}
				catch(NumberFormatException erreurAffichage) {
					JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors de l'affichage");
				}
				lireDansFichierCompteJoint.close();
				ouvreFluxLireDansFichierCompteJoint.close();
			}

		}

		class CompteEpargne extends CompteCourant{

			// Attributs
			private Double taux = (double) 0;
			String tauxEnString = new String("");

			// Méthodes
			void creerCompte() {
				super.creerCompte(); 
				if(!(this.gestionException.equals("Saisie vide"))) {
					this.tauxEnString = JOptionPane.showInputDialog(null,"Entrez le taux (en %) pour ce compte épargne :");
					try {
						if(!(tauxEnString.equals(""))){
							try {
								this.taux = Double.parseDouble(tauxEnString);
							}
							catch(NumberFormatException exceptionPasUnNombre) {
								JOptionPane.showMessageDialog(null,"Saisie non valide, taux mit à 0%");
								this.taux = (double) 0;
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Aucune saisie, taux mit à 0%");
							this.taux = (double) 0;
						}
					}
					catch(NullPointerException problemeDePointeur) {
						JOptionPane.showMessageDialog(null,"Vous avez annulé votre saisie en cours, cependant le compte est crée avec un taux de 0%");
						this.taux = (double) 0;
					}
				}
				else {

				}
			}

			void sauvegarderDansFichier(String nomDuFichier) throws IOException {
				super.sauvegarderDansFichier(nomDuFichier);
				if(!(this.gestionException.equals("Saisie vide"))) {
					fichierCompteEpargne = new File(nomDuFichier);
					FileWriter ecrireDansFichierCompteEpargne = new FileWriter(fichierCompteEpargne,true);
					BufferedWriter ouvreFluxEcrireDansFichierCompteEpargne = new BufferedWriter(ecrireDansFichierCompteEpargne);
					ouvreFluxEcrireDansFichierCompteEpargne.write(Double.toString(this.taux));
					ouvreFluxEcrireDansFichierCompteEpargne.newLine();
					ouvreFluxEcrireDansFichierCompteEpargne.close();
					ecrireDansFichierCompteEpargne.close();
				}
				else {

				}
			}

			void afficherCompte() {
				super.afficherCompte();
				System.out.println("taux : " + this.taux);
			}

			void recupererCompte(String adresseDuFichier) throws IOException{
				fichierCompteEpargne = new File(adresseDuFichier);
				FileReader lireDansFichierCompteEpargne = new FileReader(fichierCompteEpargne);
				BufferedReader ouvreFluxLireDansFichierCompteEpargne = new BufferedReader(lireDansFichierCompteEpargne);
				try {
					while ((ouvreFluxLireDansFichierCompteEpargne.readLine()) != null){
						setNumeroDeCompte(Integer.parseInt(ouvreFluxLireDansFichierCompteEpargne.readLine()));
						this.nom = ouvreFluxLireDansFichierCompteEpargne.readLine();
						this.prenom = ouvreFluxLireDansFichierCompteEpargne.readLine();
						this.dateDeNaissance = ouvreFluxLireDansFichierCompteEpargne.readLine();
						this.sexe = ouvreFluxLireDansFichierCompteEpargne.readLine();
						this.adresse = ouvreFluxLireDansFichierCompteEpargne.readLine();
						setTypeDeCompte(ouvreFluxLireDansFichierCompteEpargne.readLine());
						this.solde = ouvreFluxLireDansFichierCompteEpargne.readLine();
						this.statue = ouvreFluxLireDansFichierCompteEpargne.readLine();
						this.identifiantTitulaire = Integer.parseInt(ouvreFluxLireDansFichierCompteEpargne.readLine());
						this.taux = Double.parseDouble(ouvreFluxLireDansFichierCompteEpargne.readLine());
						if(this.statue.substring(3, 8).equals("actif")) {
							sommeSolde += Integer.parseInt(this.solde.substring(8, this.solde.length()));
							Object[] donneesCompteEpargne = {super.getNumeroDeCompte(),this.identifiantTitulaire,this.nom,this.prenom,this.dateDeNaissance,this.sexe,this.adresse,super.getTypeDeCompte(),null,this.solde.substring(8,this.solde.length()),this.taux};
							if(!interfaceAppCompte.interfaceEcrit.equals("Afficher un compte spécifique")) {
								interfaceAppCompte.tableau.addRow(donneesCompteEpargne);
							}
							else {
								if(getNumeroDeCompte() == Integer.parseInt(interfaceAppCompte.barreRechercheCompte.getText())) {
									interfaceAppCompte.tableau.addRow(donneesCompteEpargne);
								}
							}
						}
					} 
				}
				catch(NumberFormatException erreurAffichage) {
					JOptionPane.showMessageDialog(null, "Il y a eu un problème d'affichage");
				}
				lireDansFichierCompteEpargne.close();
				ouvreFluxLireDansFichierCompteEpargne.close();
			}


			void controleTaux() {
				if(this.taux < 0) {
					JOptionPane.showMessageDialog(null,"Le taux ne peut pas être négatif, initialisation à 0 ");
					this.taux = (double) 0;
				}
			}

		}

		//  Début du programme principal
		interfaceAppCompte.enleverInterface();	
		interfaceAppCompte.afficherInterfaceGestionCompte();

		String[] optionUtilisateur = {"Gestion de Compte","Afficher compte","Carte Bancaire","Aide"};

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox menuDeroulant = new JComboBox(optionUtilisateur);

		menuDeroulant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cliqueSouris) {
				menuDeroulant.setBounds(281, 11, 143, 22);
				interfaceAppCompte.reinitialiserPositionBouton();

				if(menuDeroulant.getSelectedItem() == "Gestion de Compte") {
					interfaceAppCompte.enleverInterface(); 
					interfaceAppCompte.afficherInterfaceGestionCompte();		
				}

				if(menuDeroulant.getSelectedItem() == "Afficher compte") {
					interfaceAppCompte.enleverInterface(); 
					interfaceAppCompte.afficherInterfaceAfficherCompte();
				}	

				if(menuDeroulant.getSelectedItem() == "Carte Bancaire") {
					interfaceAppCompte.enleverInterface(); 
					interfaceAppCompte.boutonVerifierCarte.setVisible(true);
				}	

				if(menuDeroulant.getSelectedItem() == "Aide") {
					interfaceAppCompte.enleverInterface(); 
					interfaceAppCompte.aideEcrit.setVisible(true);
				}

			}

		});
		menuDeroulant.setBounds(281, 11, 143, 22);
		frame.getContentPane().add(menuDeroulant);

		interfaceAppCompte.boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cliqueSouris) {
				interfaceAppCompte.enleverInterface(); 	
				menuDeroulant.setBounds(281, 11, 143, 22);
				interfaceAppCompte.reinitialiserPositionBouton();

				if(menuDeroulant.getSelectedItem() == "Gestion de Compte") {
					interfaceAppCompte.afficherInterfaceGestionCompte();		
				}

				if(menuDeroulant.getSelectedItem() == "Afficher compte") {
					interfaceAppCompte.afficherInterfaceAfficherCompte();
				}	

				if(menuDeroulant.getSelectedItem() == "Carte Bancaire") {
					interfaceAppCompte.boutonVerifierCarte.setVisible(true);
				}	

				if(menuDeroulant.getSelectedItem() == "Aide") {
					
				}

			}
		});


		interfaceAppCompte.boutonCreeCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cliqueSouris) {
				interfaceAppCompte.enleverInterface(); 			
				interfaceAppCompte.afficherInterfaceCreeCompte();
			}
		});

		interfaceAppCompte.boutonSupprimerCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cliqueSouris) {
				interfaceAppCompte.enleverInterface(); 			
				interfaceAppCompte.afficherInterfaceSupprimerCompte();
			}
		});

		interfaceAppCompte.boutonAltererCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interfaceAppCompte.enleverInterface(); 			
				interfaceAppCompte.afficherInterfaceDebiterOuCrediterCompte();
			}
		});

		interfaceAppCompte.boutonAfficherCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cliqueSouris) {
				interfaceAppCompte.tableau.setRowCount(0);
				interfaceAppCompte.enleverInterface(); 
				interfaceAppCompte.changerDimensionElements();
				menuDeroulant.setBounds(1025, 11, 143, 22);            // On change les dimensions pour mieux afficher le tableau
				interfaceAppCompte.afficherInterfaceTableauCompte();			
			}
		});

		interfaceAppCompte.boutonAfficherToutComptes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cliqueSouris) {
				int SommeSoldeToutCompte;
				interfaceAppCompte.enleverInterface(); 
				interfaceAppCompte.changerDimensionElements();
				menuDeroulant.setBounds(1025, 11, 143, 22);                  // On change les dimensions pour mieux afficher le tableau
				interfaceAppCompte.afficherInterfaceTableauToutCompte();
				CompteCourant nouveauCompteCourant = new CompteCourant();
				CompteJoint nouveauCompteJoint = new CompteJoint();
				CompteEpargne nouveauCompteEpargne = new CompteEpargne();
				interfaceAppCompte.tableau.setRowCount(0);
				try {
					nouveauCompteCourant.recupererCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteCourant.txt");
					nouveauCompteJoint.recupererCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteJoint.txt");
					nouveauCompteEpargne.recupererCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteEpargne.txt");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors de l'affichage des comptes");
				}
				SommeSoldeToutCompte = nouveauCompteCourant.sommeSolde + nouveauCompteJoint.sommeSolde + nouveauCompteEpargne.sommeSolde;
				interfaceAppCompte.sommeSoldeEcrit.setText("Somme des soldes : " + SommeSoldeToutCompte + "$");

			}
		});

		interfaceAppCompte.boutonAfficherToutComptesType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cliqueSouris) {
				interfaceAppCompte.enleverInterface(); 
				interfaceAppCompte.changerDimensionElements();
				menuDeroulant.setBounds(1025, 11, 143, 22);
				interfaceAppCompte.afficherInterfaceTableauToutCompteType();
				interfaceAppCompte.tableau.setRowCount(0);
			}
		});

		interfaceAppCompte.choixTypeCompteCourant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cliqueSouris) {
				interfaceAppCompte.tableau.setRowCount(0);
				CompteCourant nouveauCompteCourant = new CompteCourant();
				if(interfaceAppCompte.interfaceEcrit.equals("Afficher interface type compte")) {
					try {
						nouveauCompteCourant.recupererCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteCourant.txt");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors de l'affichage des comptes courants");
					}
				}
			}
		});

		interfaceAppCompte.choixTypeCompteJoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cliqueSouris) {
				interfaceAppCompte.tableau.setRowCount(0);
				CompteJoint nouveauCompteJoint = new CompteJoint();
				if(interfaceAppCompte.interfaceEcrit.equals("Afficher interface type compte")) {
					try {
						nouveauCompteJoint.recupererCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteJoint.txt");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors de l'affichage des comptes joints");
					}
				}
			}
		});

		interfaceAppCompte.choixTypeCompteEpargne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cliqueSouris) {
				interfaceAppCompte.tableau.setRowCount(0);
				CompteEpargne nouveauCompteEpargne = new CompteEpargne();
				if(interfaceAppCompte.interfaceEcrit.equals("Afficher interface type compte")) {
					try {
						nouveauCompteEpargne.recupererCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteEpargne.txt");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors de l'affichage des comptes épargnes");
					}
				}
			}
		});

		interfaceAppCompte.boutonVerifierCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cliqueSouris) {
				interfaceAppCompte.enleverInterface(); 			
				interfaceAppCompte.afficherInterfaceVerifierCarte();
				interfaceAppCompte.barreVerifierCarte.setVisible(true);
			}
		});

		interfaceAppCompte.boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cliqueSouris) {
				if(interfaceAppCompte.interfaceEcrit.contentEquals("Créer un compte")) {

					if(interfaceAppCompte.choixTypeCompteCourant.isSelected()) {
						CompteCourant nouveauCompteCourant = new CompteCourant();
						nouveauCompteCourant.creerCompte();
						try {
							nouveauCompteCourant.sauvegarderDansFichier("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteCourant.txt");
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors de la création du compte courant");
						}
					}

					if(interfaceAppCompte.choixTypeCompteJoint.isSelected()) {
						CompteJoint nouveauCompteJoint = new CompteJoint();
						nouveauCompteJoint.creerCompte();
						try {
							nouveauCompteJoint.sauvegarderDansFichier("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteJoint.txt");
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors de la création du compte joint");
						}
					}

					if(interfaceAppCompte.choixTypeCompteEpargne.isSelected()) {
						CompteEpargne nouveauCompteEpargne = new CompteEpargne();
						nouveauCompteEpargne.creerCompte();
						try {
							nouveauCompteEpargne.sauvegarderDansFichier("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteEpargne.txt");
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors de la création du compte épargne");
						}
						nouveauCompteEpargne.controleTaux();
					}
					else if(interfaceAppCompte.choixTypeCompte.isSelected(null)) {
						JOptionPane.showMessageDialog(null, "Il manque des informations");
					}

				}
				if(interfaceAppCompte.interfaceEcrit.contentEquals("Supprimer un compte")) {
					CompteBancaires nouveauCompteBancaires = new CompteBancaires();
					try {
						nouveauCompteBancaires.supprimerCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteCourant.txt");
						nouveauCompteBancaires.supprimerCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteJoint.txt");
						nouveauCompteBancaires.supprimerCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteEpargne.txt");
						if(interfaceAppCompte.barreNumeroCompte.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"Information manquante");
						}
					}
					catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors de la suppression du compte");
					}
				}
				if(interfaceAppCompte.interfaceEcrit.contentEquals("Débiter ou créditer un compte")) {
					CompteBancaires nouveauCompteBancaires = new CompteBancaires();
					try {
						nouveauCompteBancaires.altererUnCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteCourant.txt");
						nouveauCompteBancaires.altererUnCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteJoint.txt");
						nouveauCompteBancaires.altererUnCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteEpargne.txt");
						if(interfaceAppCompte.barreMontantDebiterOuCrediter.getText().equals("") || interfaceAppCompte.barreNumeroCompte.getText().equals("")) {
							JOptionPane.showMessageDialog(null,"Information manquante");
						}
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors du débitage ou du créditage");
					}
					catch(NumberFormatException montantMalSaisie) {
						JOptionPane.showMessageDialog(null, "Vous ne rentrez pas correctement le montant");		
					}
				}
				if(interfaceAppCompte.interfaceEcrit.equals("Afficher un compte spécifique")){
					CompteCourant nouveauCompteCourant = new CompteCourant();
					CompteJoint nouveauCompteJoint = new CompteJoint();
					CompteEpargne nouveauCompteEpargne = new CompteEpargne();
					interfaceAppCompte.tableau.setRowCount(0);
					try {
						nouveauCompteCourant.recupererCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteCourant.txt");
						nouveauCompteJoint.recupererCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteJoint.txt");
						nouveauCompteEpargne.recupererCompte("C://Users/Lagaillarde/eclipse-workspace-Java/BanqueMoKass/src/CompteEpargne.txt");

					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors de la récupération des données");
					}
				}
				if(interfaceAppCompte.interfaceEcrit.equals("Vérifier une carte bancaire")){
					CompteBancaires nouveauCompteBancaire = new CompteBancaires();
					nouveauCompteBancaire.verifierCarte();

				}
			}
		});

	}
}
//////////////////Fin//////////////////////////////