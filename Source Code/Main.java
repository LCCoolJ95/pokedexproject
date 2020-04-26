import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Arrays;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
/** Holds everything within Pokédex Pro */
public class Main extends Application {
	/** ID of the Pokemon, relates to national dex order */
	public int pokemonID;
	/** Each species of Pokémon has a catch rate that applies to all its members. When a Poké Ball is thrown at a wild Pokémon, the game uses
	 * that Pokémon's catch rate in a formula to determine the chances of catching that Pokémon. Higher catch rates mean that the Pokémon is
	 * easier to catch, up to a maximum of 255.*/
	public int catchRate;
	/** Each species of Pokémon has a base experience yield that applies to all its members. When a user defeats a Pokémon, they gain experience,
	 * and the higher the EXP Yield, the more experience. */
	public int baseEXPYield;
	/** Amount of steps it takes to hatch a Pokémon within an egg */
	public int eggCycle;
	/** How much the Pokémon will like you when it joins your team. The lower the value, the more it hates you. The higher the value, the more it
	 * love you. */
	public int baseFriendship;
	/** How many experience points it will take to hit the max level, level 100 */
	public int levelUpType;
	/** ID of the ability, in game data order */
	public int abilityID;
	/** ID of the move, in game data order */
	public int moveID;
	/** PP is an abbreviation of Power Points. Power Points measure the number of times a move can be used. The higher the PP, the more times you
	 * can use the move. PP are essential for attacking, supporting, stalling, recovering, and defending.*/
	public int movePP;
	/** Power is a property of moves that helps determine how much damage they deal. */
	public int moveBasePower;
	/** Accuracy is an aspect of moves that, in conjunction with the user's in-battle accuracy stat and the target's evasion stat, determines how
	 * reliable they can hit their target. */
	public int moveAccuracy;
	/** Priority is a characteristic of moves, such that any move with a higher priority than another will always be performed first. When two
	 * moves have the same priority, the users' Speed statistics will determine which one is performed first in a battle.*/
	public int speedPriority;
	/** The level is a measurement of how strong a Pokémon currently is. */
	public int pokemonLevel;
	/** Index of the list of forms for the Pokémon */
	public int otherFormIndex;
	/** The user's selection from the otherFormsList */
	public int otherFormSelection;
	/** Pokédex page number that is to be saved. */
	public int dexPage;
	/** Used to cast it from double to int */
	public int feetInt;
	/** Used to cast it from double to int */
	public int trueinchesInt;
	/** Used to calculate how many EVs were given to the Pokémon */
	public int EVTotal;
	/** Used to hold the sum of all the base stats of a Pokémon */
	public int baseTotal;
	/** Used to hold the sum of all the IVs that were given to that Pokémon */
	public int IVTotal;
	/** Used to hold the sum of all the calculated stats that were given to that Pokémon */
	public int totalStatsTotal;
	/** Name of the Pokémon */
	public String pokemonName;
	/** When a Pokémon is defeated in battle, it will give effort values to the Pokémon that participated in the battle against it. This value
	 * shows what effort values you will receive after the battle */
	public String EVYield;
	/** Gender is a characteristic of Pokémon and humans in the Pokémon world. Starting in the Generation II games, most Pokémon have a gender:
	 * male or female; however, some species of Pokémon have unknown gender. The gender mechanic is fundamental to Pokémon breeding.*/
	public String genderRatio;
	/** Beginning in Generation V, most Pokémon were given an additional, Hidden Ability. A Pokémon with its Hidden Ability will retain its
	 * ability slot upon evolution, unless the evolved form has no Hidden Ability. Hidden Abilities can only be acquired under special
	 * circumstances.*/
	public String hiddenAbility;
	/** Color of the Pokémon */
	public String pokemonColor;
	/**  In a Pokémon's Pokédex entry, the category is a name which identifies the Pokémon based on one of its defining biological
	 * characteristics. Most often, the defining traits are part of the Pokémon’s physiology, special abilities, or behavior. It was previously
	 * also known as species.*/
	public String pokemonClass;
	/** Biology of the Pokémon */
	public String pokemonBiology;
	/** Trivia/Facts about the Pokémon */
	public String pokemonTrivia;
	/** Origin of what the Pokémon is based on in real life */
	public String pokemonOrigin;
	/** Origin of the Pokémon's name */
	public String pokemonNameOrigin;
	/** The Evolution/Form explanation of the Pokémon */
	public String pokemonEvolution;
	/** Name of an ability */
	public String abilityName;
	/** Ability description grabbed from the core games */
	public String abilityshortDesc;
	/** In-depth/secondary effect of the ability */
	public String abilityInDepthDesc;
	/** Name of the move */
	public String moveName;
	/** Type of the move */
	public String moveType;
	/** The category or kind of a move is an element of said move that determines the kind of damage it deals. */
	public String moveCategory;
	/** Move description grabbed from the core games */
	public String moveShortDesc;
	/** In-depth/secondary effect of the ability */
	public String moveInDepthDesc;
	/** Natures are a mechanic that influences how a Pokémon's stats grow. They were introduced in Generation III. */
	public String pokemonNature;
	/** Username stored for the user */
	public String username;
	/** Password used to login */
	public String password;
	/** Stores the gen 7 URL into a string for the Hyperlink to read */
	public String gen7linkStr = "default";
	/** Stores the gen 8 URL into a string for the Hyperlink to read */
	public String gen8linkStr = "default";
	/** Used for the pokemonNames loop, where it temporary stores the Pokémon name from the database */
	public String pokemonNameStorage;
	/** Used for the abilityNames loop, where it temporary stores the ability name from the database */
	public String abilityNameStorage;
	/** Used for the moveNames loop, where it temporary stores the move name from the database */
	public String moveNameStorage;
	/** Used for the itemNames loop, where it temporary stores the item names from the database */
	public String itemNameStorage;
	/** Name ID of a Pokémon used to call cries and sprites */
	public String pokemonNameID;
	/** String that stores the path location of the mp3 file */
	public String cryLoc;
	/** Location of category sprite */
	public String typeLoc;
	/** Used for the pokemonNoBox event */
	public String query;
	/** Used for the itemNameBox event */
	public String itemQuery;
	/** Used for the formBox event */
	public String queryForm;
	/** String that executes to the database */
	public String saveDexPage;
	/** String that will clear the Dex page */
	public String clearDexPage;
	/** String that holds a Pokémon's name for form switching */
	public String pokemonFormNameHolder;
	/** String that holds the converted number */
	public String noToString;
	/** Species strengths, commonly referred to by fans as base stats, are the inherent values of a species or form of a species that are used to
	 * the stats of a Pokémon.<br>
	 * baseStats[0]: Base Hit Points<br>
	 * baseStats[1]: Base Attack<br>
	 * baseStats[2]: Base Defense<br>
	 * baseStats[3]: Base Special Attack<br>
	 * baseStats[4]: Base Special Defense<br>
	 * baseStats[5]: Base Speed */
	public int[] baseStats = {0, 0, 0, 0, 0, 0};
	/** A statistic, or stat for short, is an element which determines certain aspects of battles in the games. Stats may also refer to the
	 * numerical values of each field in regards to individual Pokémon.<br>
	 * realStats[0]: Hit Point stat<br>
	 * realStats[1]: Attack stat<br>
	 * realStats[2]: Defense stat<br>
	 * realStats[3]: Special Attack stat<br>
	 * realStats[4]: Special Defense stat<br>
	 * realStats[5]: Speed stat */
	public double[] realStats = {0, 0, 0, 0, 0, 0};
	/** Base points, commonly referred to by fans as effort values and abbreviated as EVs, are values that contribute to an individual Pokémon's
	 * stats in the core and side series Pokémon games. They are primarily obtained by defeating Pokémon in battle, based on the Pokémon that was
	 * defeated. From Pokémon FireRed and LeafGreen to Pokémon Ultra Sun and Ultra Moon, EVs were also officially referred as base stats in
	 * English (distinct from what fans refer to as base stats, which are instead the stat-affecting values intrinsic to the Pokémon's species).
	 * <br>
	 * EVs[0]: HP EVs<br>
	 * EVs[1]: Attack EVs<br>
	 * EVs[2]: Defense EVs<br>
	 * EVs[3]: Special Attack EVs<br>
	 * EVs[4]: Special Defense EVs<br>
	 * EVs[5]: Speed EVs */
	public int[] EVs = {0, 0, 0, 0, 0, 0};
	/** Individual strengths, abbreviated IVs from its more commonly known fan term, are the Pokémon equivalent of genes. They are instrumental
	 * in determining the stats of a Pokémon, being responsible for the large variation in stats among untrained Pokémon of the same species.<br>
	 * IVs[0]: HP IVs<br>
	 * IVs[1]: Attack IVs<br>
	 * IVs[2]: Defense IVs<br>
	 * IVs[3]: Special Attack IVs<br>
	 * IVs[4]: Special Defense IVs<br>
	 * IVs[5]: Speed IVs */
	public int[] IVs = {0, 0, 0, 0, 0, 0};
	/** Array that will hold the values of the casted ints from the realStats array */
	public int[] realStatsInt;
	/** Types are properties for Pokémon and their moves. Pokémon can have one to two types.<br>
	 * types[0]: Type 1<br>
	 * types[1]: Type 2 */
	public String[] types = {null,null};
	/** Array used to calculate the Hidden Power type */
	public int[] bits = {0, 0, 0, 0, 0, 0};
	/** An Ability is a game mechanic introduced in Generation III that provides a passive effect in battle or in the overworld. Pokémon can have
	 * one to two abilities, but can only have one.<br>
	 * abilities[0]: Ability 1<br>
	 * abilities[1]: Ability 2 */
	public String[] abilities = {null,null};
	/** Egg Groups are categories which determine which Pokémon are able to interbreed. The concept was introduced in Generation II, along with
	 * breeding. Similar to types, a Pokémon may belong to either one or two Egg Groups.<br>
	 * eggGroups[0]: Egg Group 1<br>
	 * eggGroups[1]: Egg Group 2 */
	public String[] eggGroups = {null,null};
	/** Height of a Pokémon */
	public double pokemonHeight;
	/** Weight of a Pokémon */
	public double pokemonWeight;
	/** Variable that stores inches*/
	public double inches;
	/** Variable that stores feet */
	public double feet;
	/** Variable to store inches from remainder */
	public double trueinches;
	/** Variable that stores pounds */
	public double pounds;
	/** Checks to see if a Pokémon has gender differences */
	public boolean hasFemaleForm;
	/** Checks to see if a Pokémon has other different forms/formes */
	public boolean hasOtherForms;
	/** Checks to see if the user has a saved page from a previous session */
	public boolean hasDexPageSaved;
	/** Changes the font to being bold */
	public Font fontBold = Font.font("System Bold", FontWeight.BOLD, FontPosture.REGULAR, 12);
	/** List of all the Pokémon names */
	public ObservableList<String> pokemonNamesList = FXCollections.observableArrayList();
	/** List of all the ability names */
	public ObservableList<String> abilityNamesList = FXCollections.observableArrayList();
	/** List of all the natures */
	public ObservableList<String> natureList = FXCollections.observableArrayList("Hardy","Lonely","Brave","Adamant","Naughty","Bold","Docile",
			"Relaxed","Impish","Lax","Timid","Hasty","Serious","Jolly","Naive","Modest","Mild","Quiet","Bashful","Rash","Calm","Gentle","Sassy",
			"Careful","Quirky");
	/** List of all the moves */
	public ObservableList<String> moveNamesList = FXCollections.observableArrayList();
	/** List of all the items */
	public ObservableList<String> itemNamesList = FXCollections.observableArrayList();
	/** Array for Mega Pokémon */
	public String[] megaList = {"Normal","Mega"};
	/** Array for Charizard forms */
	public String[] charizardList = {"Normal","Mega X","Mega Y","Gigantamax"};
	/** Array for Gigantamax Pokémon */
	public String[] gigantamaxList = {"Normal","Gigantamax"};
	/** Array for Alolan Pokémon */
	public String[] alolanList = {"Normal","Alolan"};
	/** Array for Meowth forms */
	public String[] meowthList = {"Normal","Alolan","Galarian","Gigantamax"};
	/** Array for Galarian forms */
	public String[] galarianList = {"Normal","Galarian"};
	/** Array for Gengar forms */
	public String[] gengarList = {"Normal","Mega","Gigantamax"};
	/** Array for Mewtwo forms */
	public String[] mewtwoList = {"Normal","Mega X","Mega Y"};
	/** Array for Unown forms */
	public String[] unownList = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","!","?"};
	/** Array for Castform forms */
	public String[] castformList = {"Normal","Sunny","Rainy","Snowy"};
	/** Array for Primal Pokémon */
	public String[] primalList = {"Normal", "Primal"};
	/** Array for Deoxys forms */
	public String[] deoxysList = {"Normal", "Attack", "Defense","Speed"};
	/** Array for Burmy/Wormadam forms */
	public String[] burmyWormadamList = {"Plant", "Sandy","Trash"};
	/** Array for Cherrim forms */
	public String[] cherrimList = {"Overcast","Sunshine"};
	/** Array for Shellos/Gastrodon forms */
	public String[] shellosGastrodonList = {"West", "East"};
	/** Array for Rotom forms */
	public String[] rotomList = {"Normal","Heat","Wash","Frost","Fan","Mow"};
	/** Array for Giratina forms */
	public String[] giratinaList = {"Altered","Origin"};
	/** Array for Shaymin forms */
	public String[] shayminList = {"Land", "Sky"};
	/** Array for Arceus/Silvally forms */
	public String[] arceusSilvallyList = {"Normal","Fighting","Flying","Poison","Ground","Rock","Bug","Ghost","Steel","Fire","Water","Grass",
			"Electric","Psychic","Ice","Dragon","Dark","Fairy"};
	/** Array for Basculin forms */
	public String[] basculinList = {"Red","Blue"};
	/** Array for Darmanitan forms */
	public String[] darmanitanList = {"Standard","Zen","Galarian","Galarian Zen"};
	/** Array for Deerling/Sawsbuck forms */
	public String[] deerlingSawsbuckList = {"Spring","Summer","Autumn","Winter"};
	/** Array for the Forces of Natures */
	public String[] TherianList = {"Incarnate", "Therian"};
	/** Array for the Kyurem forms */
	public String[] kyuremList = {"Normal","White","Black"};
	/** Array for the Keldeo forms */
	public String[] keldeoList = {"Ordinary","Resolute"};
	/** Array for the Meloetta forms */
	public String[] meloettaList = {"Aria","Pirouette"};
	/** Array for the Genesect forms */
	public String[] genesectList = {"Normal","Douse Drive","Shock Drive","Burn Drive","Chill Drive"};
	/** Array for the Greninja forms */
	public String[] greninjaList = {"Normal","Ash"};
	/** Array for the Vivillon forms */
	public String[] vivillonList = {"Meadow","Polar","Tundra","Continental","Garden","Elegant","Icy Snow","Modern","Marine","Archipelago",
			"High Plains","Sandstorm","River","Monsoon","Savanna","Sun","Ocean","Jungle","Fancy","Poké Ball"};
	/** Arrays for the different flower forms (Flabébé, Floette, Florges) */
	public String[] flowerList = {"Red","Yellow","Orange","Blue","White"};
	/** Array for the Furfrou forms */
	public String[] furfrouList = {"Natural","Heart","Star","Diamond","Deputante","Matron","Dandy","La Reine","Kabuki","Pharaoh"};
	/** Array for the "advanced" gender forms */
	public String[] genderList = {"Male","Female"};
	/** Array for the Aegislash forms */
	public String[] aegislashList = {"Shield","Blade"};
	/** Array for the Pumpkaboo/Gourgeist forms */
	public String[] pumpkabooGourgeistList = {"Average","Small","Large","Super"};
	/** Array for the Xerneas forms */
	public String[] xerneasList = {"Neutral","Active"};
	/** Array for the Zygarde forms */
	public String[] zygardeList = {"50%","10%","Complete"};
	/** Array for the Hoopa forms */
	public String[] hoopaList = {"Confined","Unbound"};
	/** Array for the Oricorio forms */
	public String[] oricorioList = {"Baile","Pom-Pom","Pa'u","Sensu"};
	/** Array for the Lycanroc forms */
	public String[] lycanrocList = {"Midday","Midnight","Dusk"};
	/** Array for the Wishwashi forms */
	public String[] wishiwashiList = {"Solo","School"};
	/** Array for the Minior forms */
	public String[] miniorList = {"Meteor","Red","Orange","Yellow","Green","Blue","Indigo","Violet"};
	/** Array for the Mimikyu forms */
	public String[] mimikyuList = {"Disgused","Busted"};
	/** Array for the Necrozma forms */
	public String[] necrozmaList = {"Normal","Dusk","Dawn","Ultra"};
	/** Array for the Magearna forms */
	public String[] magearnaList = {"Normal","Original"};
	/** Array for the Cramorant forms */
	public String[] cramorantList = {"Normal","Gulping","Gorging"};
	/** Array for the Toxtricity forms */
	public String[] toxtricityList = {"Amped Form","Low Key","Gigantamax"};
	/** Array for the Alcremie forms */
	public String[] alcremieList = {"Vanilla Cream Strawberry","Vanilla Cream Berry","Vanilla Cream Love","Vanilla Cream Star",
			"Vanilla Cream Clover","Vanilla Cream Flower","Vanilla Cream Ribbon","Ruby Cream Strawberry","Ruby Cream Berry","Ruby Cream Love",
			"Ruby Cream Star","Ruby Cream Clover","Ruby Cream Flower","Ruby Cream Ribbon","Matcha Cream Strawberry","Matcha Cream Berry",
			"Matcha Cream Love","Matcha Cream Star","Matcha Cream Clover","Matcha Cream Flower","Matcha Cream Ribbon","Mint Cream Strawberry",
			"Mint Cream Berry","Mint Cream Love","Mint Cream Star","Mint Cream Clover","Mint Cream Flower","Mint Cream Ribbon",
			"Lemon Cream Strawberry","Lemon Cream Berry","Lemon Cream Love","Lemon Cream Star","Lemon Cream Clover","Lemon Cream Flower",
			"Lemon Cream Ribbon","Salted Cream Strawberry","Salted Cream Berry","Salted Cream Love","Salted Cream Star","Salted Cream Clover",
			"Salted Cream Flower","Salted Cream Ribbon","Ruby Swirl Strawberry","Ruby Swirl Berry","Ruby Swirl Love","Ruby Swirl Star",
			"Ruby Swirl Clover","Ruby Swirl Flower","Ruby Swirl Ribbon","Caramel Swirl Strawberry","Caramel Swirl Berry","Caramel Swirl Love",
			"Caramel Swirl Star","Caramel Swirl Clover","Caramel Swirl Flower","Caramel Swirl Ribbon","Rainbow Swirl Strawberry",
			"Rainbow Swirl Berry","Rainbow Swirl Love","Rainbow Swirl Star","Rainbow Swirl Clover","Rainbow Swirl Flower","Rainbow Swirl Ribbon",
			"Gigantamax"};
	/** Array for the Eiscue forms */
	public String[] eiscueList = {"Normal","Noice Face"};
	/** Array for the Morpeko forms */
	public String[] morpekoList = {"Normal", "Hangry"};
	/** Array for the Zacian/Zamazenta forms */
	public String[] zacianZamazentaList = {"Normal","Crowned"};
	/** Array for the Eternatus */
	public String[] eternatusList = {"Normal","Eternamax"};
	/** 2D array that stores all of the forms */
	public String[][] otherForms = {megaList,charizardList,gigantamaxList,alolanList,meowthList,galarianList,gengarList,mewtwoList,unownList,
			castformList,primalList,deoxysList,burmyWormadamList,cherrimList,shellosGastrodonList,rotomList,giratinaList,shayminList,
			arceusSilvallyList,basculinList,darmanitanList,deerlingSawsbuckList,TherianList,kyuremList,keldeoList,meloettaList,genesectList,
			greninjaList,vivillonList,flowerList,furfrouList,genderList,aegislashList,pumpkabooGourgeistList,xerneasList,zygardeList,hoopaList,
			oricorioList,lycanrocList,wishiwashiList,miniorList,mimikyuList,necrozmaList,magearnaList,cramorantList,toxtricityList,alcremieList,
			eiscueList,morpekoList,zacianZamazentaList,eternatusList};
	/** List that will hold some forms */
	public ObservableList<String> otherFormsList = FXCollections.observableArrayList();
	/** Player that will play Pokémon cries */
	public AudioClip cryPlayer;
	/** Holds the cry file */
	public Media pokemonCry;
	/** SQL command used for later in the code */
	ResultSet createPokemon;
	/** The JavaFX VBox component is a layout component which positions all its child nodes (components) in a vertical column - on top of
	 * each other. */
	VBox vbox = new VBox();
	/** MenuBar is usually placed at the top of the screen which contains several menus. JavaFX MenuBar is typically an implementation of a
	 * menu bar. */
	MenuBar menubar = new MenuBar();
	/** Menu is a popup menu that contains several menu items that are displayed when the user clicks a menu. The user can select a menu item
	 * after which the menu goes into a hidden state, it is called File */
	Menu menu1 = new Menu("File");
	/** Menu is a popup menu that contains several menu items that are displayed when the user clicks a menu. The user can select a menu item
	 * after which the menu goes into a hidden state, it is called Help */
	Menu menu2 = new Menu("Help");
	/** "Bookmarks" the page so the next time the user accesses the program, it will go back to that page */
	MenuItem menuitem1 = new MenuItem("Save current page");
	/** Closes the program */
	MenuItem menuitem2 = new MenuItem("Close");
	/** Opens up a document explaining Pokémon */
	MenuItem menuitem3 = new MenuItem("Documentation");
	/** Clears a saved page if the user has one */
	MenuItem menuitem4 = new MenuItem("Clear saved page");
	/** Opens up a dialog box explaining what Pokédex Pro is */
	MenuItem menuitem5 = new MenuItem("About");
	/** Opens up a dialog box crediting who helped with the creation of this product */
	MenuItem menuitem6 = new MenuItem("Credits");
	/** AnchorPane class is a part of JavaFX. AnchorPane allows the edges of child nodes to be anchored to an offset from the anchor pane’s
	 * edges. If the anchor pane has a border and/or padding set, the offsets will be measured from the inside edge of those insets.
	 * AnchorPane inherits Pane class. */
	AnchorPane mainAnchorPane = new AnchorPane();
	/** The JavaFX TabPane is a container control which can contain multiple tabs (sections) internally, which can be displayed by clicking
	 * on the tab with the title on top of the TabPane. Only one tab is displayed at a time. It is like paper folders where one of the
	 * folders is open. */
	TabPane tabpane = new TabPane();
	/** Tab that contains the Pokédex */
	Tab pokedexTab = new Tab("Pokédex");
	/** AnchorPane that contains the Pokédex */
	AnchorPane pokedexAnchor = new AnchorPane();
	/** ComboBox that contains the Pokémon names */
	ComboBox<String> pokemonNameBox = new ComboBox<String>(pokemonNamesList);
	/** Views the sprite of the Pokémon */
	ImageView pokemonSpriteView = new ImageView();
	/** Holds the sprite of the Pokémon */
	Image pokemonsprite;
	/** Checkbox to see the shiny variation of the Pokémon */
	CheckBox shinyCheck = new CheckBox("Shiny?");
	/** Checkbox to see the female form of the Pokémon */
	CheckBox femaleCheck = new CheckBox("Female?");
	/** ComboBox that contains the forms of the Pokémon */
	ComboBox<String> formBox = new ComboBox<String>();
	/** Label that says Name: */
	Label pokemonNamelbl = new Label("Name:");
	/** Label that will hold the name of the Pokémon */
	Label pkmnNameFillIn = new Label();
	/** Button that will play the cry of the Pokémon */
	Button cryButton = new Button("Cry");
	/** Label that says Gender Ratio: */
	Label genderRatiolbl = new Label("Gender Ratio:");
	/** Label that will have the gender ratio of the Pokémon */
	Label genderRatioFillIn = new Label();
	/** Label that says Type(s) */
	Label typesLabel = new Label("Type(s)");
	/** ImageView that holds type 1 of the Pokémon */
	ImageView type1PicView = new ImageView();
	/** Image that goes into type1PicView */
	Image type1pic;
	/** ImageView that holds type 2 of the Pokémon, if they have one */
	ImageView type2PicView = new ImageView();
	/** Image that goes into type2PicView */
	Image type2pic;
	/** Label that says No. */
	Label pokemonNolbl = new Label("No.");
	/** TextField that holds the number of the Pokémon, the user can change it and go to that Pokémon #, user cannot input a number higher
	 * than the limit or lower than 1, or input non-integers */
	TextField pokemonNoBox = new TextField();
	/** Label that says Classification: */
	Label classificationLabel = new Label("Classification:");
	/** Label that will hold the Pokémon's classification */
	Label classFillIn = new Label();
	/** Label that says Height: */
	Label heightLabel = new Label("Height:");
	/** Label that will hold the height of the Pokémon in meters */
	Label heightFillIn = new Label();
	/** Label that will hold the height of the Pokémon in feet and inches */
	Label heightInchesFI = new Label();
	/** Label that says Weight: */
	Label weightLabel = new Label("Weight:");
	/** Label that will hold the weight of the Pokémon in kilograms */
	Label weightFillIn = new Label();
	/** Label that will hold the weight of the Pokémon in pounds */
	Label weightpoundsFI = new Label();
	/** Label that says Capture Rate: */
	Label captureRatelbl = new Label("Capture Rate:");
	/** Label that will hold the catch rate of the Pokémon */
	Label catchRateFI = new Label();
	/** Label that says Base Egg Steps: */
	Label baseEggStepslbl = new Label("Base Egg Steps:");
	/** Label that will hold the egg steps of the Pokémon */
	Label eggCycleFI = new Label();
	/** Label that says Experience Growth: */
	Label expGrowthlbl = new Label("Experience Growth:");
	/** Label that wll hold the EXP growth of the Pokémon */
	Label expGrowthFI = new Label();
	/** Label that says Base Happiness: */
	Label baseHappinesslbl = new Label("Base Happiness:");
	/** Label that will hold the base happiness of that Pokémon */
	Label baseHapFI = new Label();
	/** Label that says Effort Values Earned: */
	Label evsEarnedlbl = new Label("Effort Values Earned:");
	/** Label that will hold the EVs Earned from that Pokémon */
	Label evsEarnedFI = new Label();
	/** Label that says Base EXP Yield: */
	Label baseEXPlbl = new Label("Base EXP Yield:");
	/** Label that will hold the Base EXP yield to that Pokémon */
	Label BaseEXPFI = new Label();
	/** Label that says Egg Group(s): */
	Label eggGroupslbl = new Label("Egg Group(s):");
	/** Label that will hold Egg Group 1 for that Pokémon */
	Label eggGroup1 = new Label();
	/** Label that will hold Egg Group 2 for that Pokémon, if they have one */
	Label eggGroup2 = new Label();
	/** TextArea that stores the biology, trivia, name origin, and origin of that Pokémon */
	TextArea pokedexTA = new TextArea();
	/** TextArea that stores the data of whether the Pokémon evolves, changes forms, or neither */
	TextArea EvoTA = new TextArea();
	/** Label that says Color: */
	Label pokemonColorlbl = new Label("Color:");
	/** Label that stores the color of the Pokémon */
	Label pokemonColorFI = new Label();
	/** Label that says Abilities: */
	Label abilitylbl = new Label("Abilities:");
	/** Label that stores the first ability of the Pokémon */
	Label ability1FI = new Label();
	/** Label that stores the second ability of the Pokémon, if they have one */
	Label ability2FI = new Label();
	/** Label that stores the hidden ability of the Pokémon, if they have one */
	Label hiddenAbilityFI = new Label();
	/** Label that holds Rockruff's event-only ability, Own Tempo */
	Label rockruffAbilityFI = new Label("Own Tempo");
	/** Tab that contains abilities */
	Tab abilityTab = new Tab("Abilities");
	/** Tabs for moves */
	Tab movesTab = new Tab("Moves");
	/** AnchorPane that contains the Abilities */
	AnchorPane abilityAnchor = new AnchorPane();
	/** ComboBox that has all the abilities, where a user can select a ability to look at */
	ComboBox<String> abilityComboBox = new ComboBox<String>(abilityNamesList);
	/** Label that says Name */
	Label abilityNamelbl = new Label("Name:");
	/** Label that stores the name of the ability */
	Label abilityNameFI = new Label();
	/** Label that says Game's Text */
	Label gameTextlbl = new Label("Game's Text");
	/** TextArea that stores the shorter ability description */
	TextArea gameTextFI = new TextArea();
	/** Label that says In-Depth Effect */
	Label inDepthEffectlbl = new Label("In-Depth Effect");
	/** TextArea that stores a more specific ability description */
	TextArea inDepthEffectFI = new TextArea();
	/** Label that says Wanna learn about the Pokémon chosen competitively? Click the link(s) below: */
	Label competitivelbl = new Label("Wanna learn about the Pokémon chosen competitively? Click the link(s) below:");
	/** Label that says Gen 7: */
	Label gen7lbl = new Label("Gen 7:");
	/** Hyperlink that will hold the link to the Pokémon's Gen 7 competitive page */
	Hyperlink gen7link = new Hyperlink();
	/** Label that says Gen 8: */
	Label gen8lbl = new Label("Gen 8:");
	/** Hyperlink that will hold the link to the Pokémon's Gen 8 competitive page */
	Hyperlink gen8link = new Hyperlink();
	/** AnchorPane that contains the moves */
	AnchorPane moveAnchor = new AnchorPane();
	/** ComboBox that contains all the moves that the user can choose a move */
	ComboBox<String> movesBox = new ComboBox<String>(moveNamesList);
	/** Label that says Move Name */
	Label moveNamelbl = new Label("Move Name:");
	/** Label that stores the name of the move */
	Label moveNameFI = new Label();
	/** Label that says Move Type */
	Label moveTypelbl = new Label("Move Type");
	/** ImageView that shows the sprite of the move type */
	ImageView moveTypePicView = new ImageView();
	/** Image that holds the sprite of the move type*/
	Image moveTypePic;
	/** Label that says Move Category */
	Label categorylbl = new Label("Move Category");
	/** ImageView that loads the sprite of the move category */
	ImageView categoryPicSmallView = new ImageView();
	/** Image used to store the sprite of the Physical AND Special categories*/
	Image categoryBigPic;
	/** ImageView that loads both physical and special sprites (For Z-Moves/Max Moves) */
	ImageView categorypicView = new ImageView();
	/** Image used to store the sprite of either Physical OR Special categories*/
	Image categorySmallPic;
	/** Label that says Power Points */
	Label powerPointslbl = new Label("Power Points");
	/** Label that stores the PP of the move */
	Label movePPFI = new Label();
	/** Label that says Base Power */
	Label basePowerlbl = new Label("Base Power");
	/** Label that stores the base power of the move */
	Label moveBasePowerFI = new Label();
	/** Label that says Accuracy */
	Label accuracylbl = new Label("Accuracy");
	/** Label that stores the move accuracy of the move */
	Label moveAccuracyFI = new Label();
	/** Label that says Speed Priority */
	Label speedPrioritylbl = new Label("Speed Priority");
	/** Label that stores the speed priority of the move */
	Label speedPriorityFI = new Label();
	/** Label that says Battle Effect: */
	Label battleEffectlbl = new Label("Battle Effect:");
	/** TextArea that stores the shorter move description of that move */
	TextArea shortDescMoveFI = new TextArea();
	/** Label that says Secondary/In-Depth Effect */
	Label secondaryEffectlbl = new Label("Secondary/In-Depth Effect:");
	/** TextArea that stores the longer move description of that move */
	TextArea inDepthDescMove = new TextArea();
	/** Tab that contains the Stat Calculator */
	Tab statCalcTab = new Tab("Stat Calc");
	/** AnchorPane that contains the stat calculator */
	AnchorPane statCalcAnchor = new AnchorPane();
	/** Label that says HP: */
	Label hplbl = new Label("HP:");
	/** Label that says Attack: */
	Label attacklbl = new Label("Attack:");
	/** Label that says Defense: */
	Label defenselbl = new Label("Defense:");
	/** Label that says Special Attack: */
	Label spAtklbl = new Label("Special Attack:");
	/** Label that says Special Defense: */
	Label spDeflbl = new Label("Special Defense:");
	/** Label that says Speed */
	Label speedlbl = new Label("Speed:");
	/** TextField that stores the base HP of that Pokémon */
	TextField baseHPTF = new TextField();
	/** TextField that stores the base Defense of that Pokémon */
	TextField baseDefTF = new TextField();
	/** Label that stores the base Attack of that Pokémon */
	TextField baseAtkTF = new TextField();
	/** TextField that stores the base Speed of that Pokémon */
	TextField baseSpeTF = new TextField();
	/** TextField that stores the base Special Defense of that Pokémon */
	TextField baseSpDefTF = new TextField();
	/** TextField that stores the base Special Attack of that Pokémon */
	TextField baseSpAtkTF = new TextField();
	/** TextField that a user can input the HP IVs */
	TextField HPIVsTF = new TextField();
	/** TextField that a user can input the Defense IVs */
	TextField defIVsTF = new TextField();
	/** TextField that a user can input the Attack IVs */
	TextField atkIVsTF = new TextField();
	/** TextField that a user can input the Speed IVs */
	TextField speIVsTF = new TextField();
	/** TextField that a user can input the Special Defense IVs */
	TextField spDefIVsTF = new TextField();
	/** TextField that a user can input the Special Attack IVs */
	TextField spAtkIVsTF = new TextField();
	/** TextField that a user can input the HP EVs */
	TextField HPEVsTF = new TextField();
	/** TextField that a user can input the Defense EVs */
	TextField defEVsTF = new TextField();
	/**  TextField that a user can input the Attack EVs */
	TextField atkEVsTF = new TextField();
	/** TextField that a user can input the Speed EVs */
	TextField speEVsTF = new TextField();
	/** TextField that a user can input Special Defense EVs */
	TextField spDefEVsTF = new TextField();
	/** TextField that a user can input Special Attack EVs */
	TextField spAtkEVsTF = new TextField();
	/** TextField that prints the sum of all the base stats */
	TextField totalBaseStatsTF = new TextField();
	/** TextField that prints the sum of the IVs inputted */
	TextField totalIVsTF = new TextField();
	/** TextField that prints the sum of the EVs inputted */
	TextField totalEVsTF = new TextField();
	/** TextField that prints the calculated HP stat */
	TextField hpStatTF = new TextField();
	/** TextField that prints the calculated Defense stat */
	TextField defStatTF = new TextField();
	/** TextField that prints the calculated Attack stat */
	TextField atkStatTF = new TextField();
	/** TextField that prints the calculated Speed stat */
	TextField speStatTF = new TextField();
	/** TextField that prints the calculated Special Defense stat */
	TextField spDefStatTF = new TextField();
	/** TextField that prints the calculated Special Attack stat */
	TextField spAtkStatTF = new TextField();
	/** TextField that prints the sum of the calculated stats */
	TextField totalStatsTF = new TextField();
	/** Label that says Total: */
	Label totallbl = new Label("Total:");
	/** Label that says Base */
	Label baselbl = new Label("Base");
	/** Label that says IVs */
	Label ivslbl = new Label("IVs");
	/** Label that says EVs */
	Label evslbl = new Label("EVs");
	/** Label that says Stats */
	Label statslbl = new Label("Stats");
	/** ComboBox that contains a list of natures that the user can select */
	ComboBox<String> natureCombobox = new ComboBox<String>(natureList);
	/** Label that says Nature: */
	Label naturelbl = new Label("Nature:");
	/** TextField where a user can input a level between 1 and 100 */
	TextField levelTF = new TextField();
	/** Label that says Level */
	Label levellbl = new Label("Level:");
	/** Button that calculates the stats of the Pokémon */
	Button getStatsButton = new Button("Get Stats");
	/** Tab that contains item information */
	Tab itemsTab = new Tab("Items");
	/** AnchorPane for the Items tab */
	AnchorPane itemAnchor = new AnchorPane();
	/** ComboBox for the user to select an item to look at*/
	ComboBox<String> itemNameBox = new ComboBox<String>(itemNamesList);
	/** Label that says Item Name: */
	Label itemNamelbl = new Label("Item Name:");
	/** Label that says Item Pocket:*/
	Label itemPocketlbl = new Label("Item Pocket:");
	/** Label that will hold the item's name */
	Label itemNameFI = new Label();
	/** Label that will hold the item pocket */
	Label itemPocketFI = new Label();
	/** ImageView that will show the item picture */
	ImageView itemImageView = new ImageView();
	/** Image that will store the picture of the item */
	Image itemImage;
	/** Label that says Item Description:*/
	Label itemDescriptionlbl = new Label("Item Description:");
	/** Text area that will store the shorter, in-game description, of an item */
	TextArea itemDescTA = new TextArea();
	/** Label that says Item Description (In-depth): */
	Label itemDescriptionLongerlbl = new Label("Item Description (In-depth):");
	/** TextArea that will store the longer item description */
	TextArea itemDescLongerTA = new TextArea();
	/** Scene that holds the entire Pokédex Pro */
	Scene scene = new Scene(vbox);
	/** Image that stores the shiny female version of that Pokémon */
	Image shinyFemalePoke;
	/** Statement variable used to write query statements to the database */
	Statement statement;
	/** ResultSet used to get all the Pokémon names */
	ResultSet pokemonNames;
	/** ResultSet used to get all the ability names */
	ResultSet abilityNames;
	/** ResultSet used to get all the move names */
	ResultSet moveNames;
	/** ResultSet that gets a certain ability to be read */
	ResultSet getAbility;
	/** ResultSet that gets a certain move to be read */
	ResultSet getMove;
	/** ResultSet that gets a certain item to be read */
	ResultSet getItem;
	/** ResultSet that gets a certain Pokémon using their dex number*/
	ResultSet createPokemonNo;
	/** ResultSet that gets the form of the Pokémon */
	ResultSet getForm;
	/** VBox to hold the login screen */
	VBox login = new VBox();
	/** AnchorPane to be added to the login VBox*/
	AnchorPane loginAnchor = new AnchorPane();
	/** Label that says Welcome to Pokédex Pro! */
	Label welcomeTolbl = new Label("Welcome to Pokédex Pro!");
	/** Label that says Please enter your login info below */
	Label pleaseEnterlbl = new Label("Please enter your login info below");
	/** Label that says Username: */
	Label usernamelbl = new Label("Username:");
	/** Label that says Password: */
	Label passwordlbl = new Label("Password:");
	/** TextField for the user to enter their username */
	TextField usernameFI = new TextField();
	/** PasswordField for the user to enter their password */
	PasswordField passwordFI = new PasswordField();
	/** Label that says New here? */
	Label newHerelbl = new Label("New here?");
	/** Button to press after the user inputs all their data */
	Button loginbtn = new Button("Login");
	/** Button that closes out the program */
	Button cancelbtn = new Button("Cancel");
	/** Hyperlink that lets the user create an account */
	Hyperlink createAccountHL = new Hyperlink("Create an account!");
	/** AnchorPane to be added to the VBox later */
	AnchorPane createAnchor = new AnchorPane();
	/** Label that says Create an Account */
	Label createAccountlbl = new Label("Create an Account");
	/** Label that says First Name: */
	Label firstNamelbl = new Label("First Name:");
	/** TextField for the user to enter their first name */
	TextField firstNameFI = new TextField();
	/** Label that says Last Name: */
	Label lastNamelbl = new Label("Last Name:");
	/** TextField for the user to enter their last name */
	TextField lastNameFI = new TextField();
	/** Label that says Username: */
	Label createUNlbl = new Label("Username:");
	/** TextField for the user to enter a username */
	TextField createUNFI = new TextField();
	/** Label that says Password: */
	Label createPWlbl = new Label("Password:");
	/** PasswordField for the user to create their password */
	PasswordField createPWFI = new PasswordField();
	/** Button that creates an account */
	Button createAccountbtn = new Button("Create Account");
	/** Button that cancels the Create Account process */
	Button cancelbtnCA = new Button("Cancel");
	/** String used to get the info from the login info table of the database */
	String getLoginInfo;
	/** ResultSet to get the login info for the username to see if they matched up  */
	ResultSet getLogin;
	/** String used to get the data about a specific Pokédex page */
	String getDexPage;
	/** ResultSet to get the info about a Pokédex page */
	ResultSet getDex;
	/** ResultSet to get all the item names*/
	ResultSet itemNames;
	/** String to be used to create an account */
	String createAccountStatement;
	/** Parameter for the start method */
	Stage mainStage = new Stage();
	/** VBox for creating an account */
	VBox createAccount = new VBox();
	/** Sets the scene to the login VBox */
	Scene loginScene = new Scene(login);
	/** Sets a stage as the login screen */
	Stage loginStage = new Stage(); // 
	/** Create a scene called createAccountScene */
	Scene createAccountScene = new Scene(createAccount);
	/** Creates a stage called createStage */
	Stage createStage = new Stage();
	/** Image of what type Hidden Power will be */
	Image HPImage;
	/** View to put hiddenPowerImage in */
	ImageView HPImageView = new ImageView();
	/** Label that says Hidden Power Type: */
	Label HPTypelbl = new Label("Hidden Power Type:");
	/** Connection to the database */
	Connection connection;
	
	/**
	 * This main part makes the program launch
	 	* @param args 
	 */
	public static void main(String[] args) { // This main part makes the program launch
		launch(args); // Launches start, I believe
	} // End of this main value
	/**
	 * The creates the GUI and connects the database
	 */
	@Override
	public void start(Stage placeStage) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver"); // Loads the JDBC driver
		/** Connects to the database */
		connection = DriverManager.getConnection("jdbc:mysql://localhost/pokedatabase", "root", "sesame");
		statement = connection.createStatement(); // Used to write query statements to the database
		/** Query statement that grabs all the Pokémon names */
		pokemonNames = statement.executeQuery("SELECT pokemon_name FROM pokemon LIMIT 890");
		while(pokemonNames.next()) { // While loop that fills pokemonNamesList with the names of the Pokémon from the database
			/** takes the data at the whatever row in column 1 (pokemon_name), and puts it into pokemonNameStorage */
			pokemonNameStorage = pokemonNames.getString(1); 
			pokemonNamesList.add(pokemonNameStorage); // adds what is in pokemonNameStorage to the pokemonNamesList
		} // End of while loop
		/** Query statement that grabs all the ability names */
		abilityNames = statement.executeQuery("SELECT ability_name FROM abilities ORDER BY ability_id;");
		while (abilityNames.next()) { // While loop that fills abilityNamesList with the names of the abilities from the database
			/** takes the data at the whatever row in column 1 (ability_name), and puts it into abilityNameStorage */
			abilityNameStorage = abilityNames.getString(1);
			abilityNamesList.add(abilityNameStorage); // adds what is in abilityNameStorage to the abilityNamesList
		} // End of while loop
		moveNames = statement.executeQuery("SELECT move_name FROM moves ORDER BY moves_id;"); // Query statement that grabs all the move names
		while (moveNames.next()) { // While loop that fills moveNamesList with the names of the moves from the database
			/** takes the data at the whatever row in column 1 (move_name), and puts it into moveNameStorage */
			moveNameStorage = moveNames.getString(1);
			moveNamesList.add(moveNameStorage); // adds what is in moveNameStorage to the moveNamesList
		} // End of while loop
		itemNames = statement.executeQuery("SELECT * FROM items;"); // Query statement that grabs all the move names
		while (itemNames.next()) { // While loop that fills itemNamesList with the names of the items from the database
			/** takes the data at the whatever row in column 3 (item_name), and puts it into itemNameStorage */
			itemNameStorage = itemNames.getString(3);
			itemNamesList.add(itemNameStorage); // adds what is in itemNameStorage to the itemNamesList
		} // End of while loop
		pokemonNameID = "default"; // Sets the pokemonNameID back to "default"
		vbox.setPrefHeight(703.0); // Sets the height of vbox to 703
		vbox.setPrefWidth(908); // Sets the width of vbox to 908
		menubar.setPrefWidth(640); // Sets the width of menubar to 640
		menuitem1.setDisable(true); // Disables menuitem1
		menuitem4.setDisable(true); // Disables menuitem4
		menu1.getItems().addAll(menuitem1,menuitem4,menuitem2); // Adds File, Help, Clear saved page, and Close to File
		menu2.getItems().addAll(menuitem3,menuitem5,menuitem6); // Adds Documentation, About, and Credits to Help
		menubar.getMenus().addAll(menu1,menu2); // Adds the menus to the menubar
		mainAnchorPane.setPrefHeight(462); // Sets the height of mainAnchorPane to 462
		mainAnchorPane.setPrefWidth(640); // Sets the width of mainAnchorPane to 640
		tabpane.setPrefHeight(677); // Sets the height of tabpane to 677
		tabpane.setPrefWidth(908); // Sets the width of tabpane to 908
		tabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); // Prevents the user from closing any tabs
		pokedexAnchor.setPrefHeight(180); // Sets the height of pokedexAnchor to 180
		pokedexAnchor.setPrefWidth(200); // Sets the width of pokedexAnchor to 200
		pokemonNameBox.setLayoutX(379); // Sets the x location of pokemonNameBox to 379
		pokemonNameBox.setLayoutY(4); // Sets the y location of pokemonNameBox to 4
		pokemonNameBox.setPromptText("Select Pokémon"); // Sets the prompt text of pokemonNameBox to Select Pokémon
		pokemonSpriteView.setFitHeight(250); // Sets the height of pokemonSpriteView to 250
		pokemonSpriteView.setFitWidth(250); // Sets the width of pokemonSpriteView to 250
		pokemonSpriteView.setLayoutX(329); // Sets the x location of pokemonSpriteView to 329
		pokemonSpriteView.setLayoutY(30); // Sets the y location of pokemonSpriteView to 30
		shinyCheck.setLayoutX(704); // Sets the x location of shinyCheck to 704
		shinyCheck.setLayoutY(58); // Sets the y location of shinyCheck to 58
		/** Tooltip to explain shiny Pokémon */
		shinyCheck.setTooltip(new Tooltip("A Shiny Pokémon, previously officially known as alternate coloration or rare coloration, and called\n"
				+ "Color Pokémon in Pokémon Stadium 2, is a specific Pokémon with different coloration to what is\n"
				+ "usual for its species. It is one of the many differences that a Pokémon can have within its species. "));
		shinyCheck.setVisible(false); // Makes shinyCheck invisible
		femaleCheck.setLayoutX(704); // Sets the x location of femaleCheck to 704
		femaleCheck.setLayoutY(82); // Sets the y location of femaleCheck to 82
		femaleCheck.setTooltip(new Tooltip("Some species of Pokémon have differences in appearance due to gender in the Pokémon world, with\n"
				+ "noticeable differences between the males and females.")); // Tooltip to explain male/female differences
		femaleCheck.setVisible(false); // Makes femaleCheck invisible
		formBox.setLayoutX(657); // Sets the x location of formBox to 657
		formBox.setLayoutY(129); // Sets the y location of formBox to 129
		formBox.setPrefWidth(150); // Sets the width of formBox to 150
		/** Tooltip to explain forms */
		formBox.setTooltip(new Tooltip("Some Pokémon have official major variations between individuals, known as forms. These variations all\n"
				+ "have differing sprites in the games and are recognized by the \"forms\" section of the Pokédex."));
		formBox.setVisible(false); // Makes formBox invisible
		pokemonNamelbl.setLayoutX(22); // Sets the x location of pokemonNamelbl to 22
		pokemonNamelbl.setLayoutY(8); // Sets the y location of pokemonNamelbl to 8
		pokemonNamelbl.setFont(fontBold); // Sets the font of pokemonNamelbl to bold
		pokemonNamelbl.setTooltip(new Tooltip("Name of the Pokémon")); // Tooltip for Pokémon name
		pkmnNameFillIn.setLayoutX(22); // Sets the x location of pkmnNameFillIn to 22
		pkmnNameFillIn.setLayoutY(25); // Sets the y location of pkmnNameFillIn to 25
		cryButton.setLayoutX(437); // Sets the x location of cryButton to 437
		cryButton.setLayoutY(285); // Sets the y location of cryButton to 285
		cryButton.setFont(fontBold); // Sets the font within cryButton to bold
		cryButton.setVisible(false); // Sets the visibility of cryButton to false
		/** Tooltip to explain what the Cry button does */
		cryButton.setTooltip(new Tooltip("Button that will play the cry of the Pokémon"));
		genderRatiolbl.setLayoutX(169); // Sets the x location of genderRatiolbl to 164
		genderRatiolbl.setLayoutY(8); // Sets the y location of genderRatiolbl to 8
		genderRatiolbl.setFont(fontBold); // Sets the font of genderRatiolbl to bold
		genderRatiolbl.setTooltip(new Tooltip("Gender is a characteristic of Pokémon and humans in the Pokémon world. Starting in the\n"
				+ "Generation II games, most Pokémon have a gender: male or female; however, some\n"
				+ "species of Pokémon have unknown gender. The gender mechanic is fundamental to\n"
				+ "Pokémon breeding.")); // Tooltip explaining Pokémon genders
		genderRatioFillIn.setLayoutX(169); // Sets the x location of genderRatioFillIn to 164
		genderRatioFillIn.setLayoutY(25); // Sets the y location of genderRatioFillIn to 25
		typesLabel.setLayoutX(821); // Sets the x location of typesLabel to 821
		typesLabel.setLayoutY(8); // Sets the y location of typesLabel to 8
		typesLabel.setFont(fontBold); // Sets the font of typesLabel to bold
		typesLabel.setTooltip(new Tooltip("Property of a Pokémon. Can have one to two types")); // Tooltip explaining that Pokémon have types
		type1PicView.setFitHeight(12); // Sets the height of type1PicView to 12
		type1PicView.setFitWidth(32); // Sets the width of type1PicView to 32
		type1PicView.setLayoutX(826); // Sets the x location of type1PicView to 826
		type1PicView.setLayoutY(25); // Sets the y location of type1PicView to 25
		type2PicView.setFitHeight(12); // Sets the height of type2PicView to 12
		type2PicView.setFitWidth(32); // Sets the width of type2PicView to 32
		type2PicView.setLayoutX(826); // Sets the x location of type2PicView to 826
		type2PicView.setLayoutY(37); // Sets the y location of type2PicView to 37
		pokemonNolbl.setLayoutX(270); // Sets the x location of pokemonNolbl to 270
		pokemonNolbl.setLayoutY(7); // Sets the y location of pokemonNolbl to 7
		pokemonNolbl.setFont(fontBold); // Sets the font of pokemonNolbl to bold
		pokemonNolbl.setTooltip(new Tooltip("National Pokédex # of Pokémon")); // Tooltip explaining the Pokémon # box/label
		pokemonNoBox.setLayoutX(294); // Sets the x location of pokemonNoBox to 294
		pokemonNoBox.setLayoutY(4); // Sets the y location of pokemonNoBox to 4
		pokemonNoBox.setPrefHeight(26); // Sets the height of pokemonNoBox to 26
		pokemonNoBox.setPrefWidth(79); // Sets the width of pokemonNoBox to 79
		classificationLabel.setLayoutX(22); // Sets the x location of classificationLabel to 22
		classificationLabel.setLayoutY(46); // Sets the y location of classificationLabel to 46
		classificationLabel.setFont(fontBold); // Sets the font of classificationLabel to bold
		/** Tooltip explaining Pokémon classifications */
		classificationLabel.setTooltip(new Tooltip("In a Pokémon's Pokédex entry, the category is a name which identifies the Pokémon based on\n"
				+ "one of its defining biological characteristics. Most often, the defining traits are part of the\n"
				+ "Pokémon’s physiology, special abilities, or behavior. It was previously also known as species."));
		classFillIn.setLayoutX(22); // Sets the x location of classFillIn to 22
		classFillIn.setLayoutY(63); // Sets the y location of classFillIn to 63
		heightLabel.setLayoutX(22); // Sets the x location of heightLabel to 22
		heightLabel.setLayoutY(82); // Sets the y location of heightLabel to 82
		heightLabel.setFont(fontBold); // Sets the font of heightLabel to bold
		heightLabel.setTooltip(new Tooltip("Height of a Pokémon, both in meters and feet/inches")); // Tooltip explaining Pokémon's height
		heightFillIn.setLayoutX(22); // Sets the x location of heightFillIn to 22
		heightFillIn.setLayoutY(101); // Sets the y location of heightFillIn to 101
		heightInchesFI.setLayoutX(22); //  Sets the x location of heightInchesFI to 22
		heightInchesFI.setLayoutY(120); // Sets the y location of heightInchesFI to 120
		weightLabel.setLayoutX(22); // Sets the x location of weightLabel to 22
		weightLabel.setLayoutY(140); // Sets the y location of weightLabel to 140
		weightLabel.setFont(fontBold); // Sets the font of weightLabel to bold
		weightLabel.setTooltip(new Tooltip("Weight of a Pokémon, both in pounds and kilograms")); // Tooltip explaining Pokémon's weight
		weightFillIn.setLayoutX(22); // Sets the x location of weightFillIn to 22
		weightFillIn.setLayoutY(157); // Sets the y location of weightFillIn to 157
		weightpoundsFI.setLayoutX(22); // Sets the x location of weightpoundsFI to 22
		weightpoundsFI.setLayoutY(176); // Sets the y location of weightpoundsFI to 176
		captureRatelbl.setLayoutX(169); // Sets the x location of captureRatelbl to 164
		captureRatelbl.setLayoutY(46); // Sets the y location of captureRatelbl to 46
		captureRatelbl.setFont(fontBold); // Sets the font of captureRatelbl to bold
		captureRatelbl.setTooltip(new Tooltip("Each species of Pokémon has a catch rate that applies to all its members. When a Poké Ball is\n"
				+ "thrown at a wild Pokémon, the game uses that Pokémon's catch rate in a formula to determine\n"
				+ "the chances of catching that Pokémon. Higher catch rates mean that the Pokémon is easier to\n"
				+ "catch, up to a maximum of 255.")); // Tooltip explaining capture rate
		catchRateFI.setLayoutX(169); // Sets the x location of catchRateFI to 164
		catchRateFI.setLayoutY(63); // Sets the y location of catchRateFI to 63
		baseEggStepslbl.setLayoutX(169); // Sets the x location of baseEggStepslbl to 164
		baseEggStepslbl.setLayoutY(82); // Sets the y location of baseEggStepslbl to 82
		baseEggStepslbl.setFont(fontBold); // Sets the font of baseEggStepslbl to bold
		/** Tooltip explaining Pokémon egg steps */
		baseEggStepslbl.setTooltip(new Tooltip("Amount of steps it takes to hatch a Pokémon within an egg"));
		eggCycleFI.setLayoutX(169); // Sets the x location of eggCycleFI to 164
		eggCycleFI.setLayoutY(101); // Sets the y location of eggCycleFI to 101
		expGrowthlbl.setLayoutX(22); // Sets the x location of expGrowthlbl to 22
		expGrowthlbl.setLayoutY(193); // Sets the y location of expGrowthlbl to 193
		expGrowthlbl.setFont(fontBold); // Sets the font of expGrowthlbl to bold
		/** Tooltip explaining EXP growth */
		expGrowthlbl.setTooltip(new Tooltip("How many experience points it will take to hit the max level, level 100"));
		expGrowthFI.setLayoutX(22); // Sets the x location of expGrowthFI to 22
		expGrowthFI.setLayoutY(209); // Sets the y location of expGrowthFI to 209
		baseHappinesslbl.setLayoutX(169); // Sets the x location of baseHappinesslbl to 164
		baseHappinesslbl.setLayoutY(193); // Sets the y location of baseHappinesslbl to 193
		baseHappinesslbl.setFont(fontBold); // Sets the font of baseHappinesslbl to bold
		baseHappinesslbl.setTooltip(new Tooltip("How much the Pokémon will like you when it joins your team.\n"
				+ "The lower the value, the more it hates you. The higher the\n"
				+ "value, the more it love you.")); // Tooltip explaining base happiness/friendship
		baseHapFI.setLayoutX(169); // Sets the x location of baseHapFI to 164
		baseHapFI.setLayoutY(209); // Sets the y location of baseHapFI to 209
		evsEarnedlbl.setLayoutX(22); // Sets the x location of evsEarnedlbl to 22
		evsEarnedlbl.setLayoutY(227); // Sets the y location of evsEarnedlbl to 227
		evsEarnedlbl.setFont(fontBold); // Sets the font of evsEarnedlbl to bold
		/** Tooltip explaining what this section means */
		evsEarnedlbl.setTooltip(new Tooltip("When a Pokémon is defeated in battle, it will give effort values to the Pokémon that participated\n"
				+ "in the battle against it. This value shows what effort values you will receive after the battle"));
		evsEarnedFI.setLayoutX(22); // Sets the x location of evsEarnedFI to 22
		evsEarnedFI.setLayoutY(247); // Sets the y location of evsEarnedFI to 247
		baseEXPlbl.setLayoutX(169); // Sets the x location of baseEXPlbl to 164
		baseEXPlbl.setLayoutY(226); // Sets the y location of baseEXPlbl to 226
		baseEXPlbl.setFont(fontBold); // Sets the font of baseEXPlbl to bold
		/** Tooltip explaining Base EXP yield */
		baseEXPlbl.setTooltip(new Tooltip("Each species of Pokémon has a base experience yield that applies to all its members. When a user\n"
				+ "defeats a Pokémon, they gain experience, and the higher the EXP Yield, the more experience."));
		BaseEXPFI.setLayoutX(169); // Sets the x location of BaseEXPFI to 164
		BaseEXPFI.setLayoutY(247); // Sets the y location of BaseEXPFI to 247
		eggGroupslbl.setLayoutX(169); // Sets the x location of eggGroupslbl to 164
		eggGroupslbl.setLayoutY(140); // Sets the y location of eggGroupslbl to 140
		eggGroupslbl.setFont(fontBold); // Sets the font of eggGroupslbl to bold
		eggGroupslbl.setTooltip(new Tooltip("Egg Groups are categories which determine which Pokémon are able to interbreed. The concept was\n"
				+ "introduced in Generation II, along with breeding. Similar to types, a Pokémon may belong to either\n"
				+ "one or two Egg Groups.")); // Tooltip explaining egg groups
		eggGroup1.setLayoutX(169); // Sets the x location of eggGroup1 to 164
		eggGroup1.setLayoutY(157); // Sets the y location of eggGroup1 to 157
		eggGroup2.setLayoutX(169); // Sets the x location of eggGroup2 to 164
		eggGroup2.setLayoutY(176); // Sets the y location of eggGroup2 to 176
		pokedexTA.setEditable(false); // Sets pokedexTA to NOT be editable
		pokedexTA.setLayoutX(6); // Sets the x location of pokedexTA to 6
		pokedexTA.setLayoutY(317); // Sets the y location of pokedexTA to 317
		pokedexTA.setPrefHeight(225); // Sets the height of pokedexTA to 324
		pokedexTA.setPrefWidth(894); // Sets the width of pokedexTA to 894
		pokedexTA.setWrapText(true); // Sets pokedexTA to have its text wrap around
		EvoTA.setLayoutX(612); // Sets the x location of EvoTA to 612
		EvoTA.setLayoutY(161); // Sets the y location of EvoTA to 161
		EvoTA.setPrefHeight(148); // Sets the height of EvoTA to 148
		EvoTA.setPrefWidth(230); // Sets the width of EvoTA to 230
		EvoTA.setEditable(false); // Sets EvoTA to NOT be editable
		EvoTA.setWrapText(true); // Sets EvoTA to have its text wrap around
		pokemonColorlbl.setLayoutX(22); // Sets the x location of pokemonColorlbl to 22
		pokemonColorlbl.setLayoutY(276); // Sets the y location of pokemonColorlbl to 276
		pokemonColorlbl.setFont(fontBold); // Sets the font of pokemonColorlbl to bold
		pokemonColorlbl.setTooltip(new Tooltip("Color of the Pokémon")); // Tooltip explaining color
		pokemonColorFI.setLayoutX(62); // Sets the x location of pokemonColorFI to 62
		pokemonColorFI.setLayoutY(276); // Sets the y location of pokemonColorFI to 276
		abilitylbl.setLayoutX(539); // Sets the x location of abilitylbl to 539
		abilitylbl.setLayoutY(8); // Sets the y location of abilitylbl to 8
		abilitylbl.setFont(fontBold); // Sets the font of abilitylbl to bold.
		abilitylbl.setTooltip(new Tooltip("An Ability is a game mechanic introduced in Generation III that provides a passive effect\n"
				+ "in battle or in the overworld. Pokémon can have one to two abilities, but can only have\n"
				+ "one.")); // Tooltip explaining what an ability is
		hiddenAbilityFI.setFont(fontBold); // Sets the font of hiddenAbility to bold.
		rockruffAbilityFI.setLayoutX(598); // Sets the x location of rockruffAbilityFI to 598
		rockruffAbilityFI.setLayoutY(60); // Sets the y location of rockruffAbilityFI to 60
		rockruffAbilityFI.setVisible(false); // Sets the visibility of rockruffAbilityFI to false
		rockruffAbilityFI.setFont(fontBold); // Sets the font of rockruffAbilityFI to bold
		rockruffAbilityFI.setTextFill(Color.web("#FF0000")); // Sets the color of rockruffAbilityFI to Red
		competitivelbl.setLayoutX(16); // Sets the x location of competitivelbl to 16
		competitivelbl.setLayoutY(552); // Sets the y location of competitivelbl to 527
		competitivelbl.setFont(new Font("System Bold",20)); // Sets the font of competitivelbl to bold and size 20 font
		/** Tooltip explaining what competitive section is */
		competitivelbl.setTooltip(new Tooltip("Links that will take you to Smogon, where you learn competitive sets of the Pokémon"));
		gen7lbl.setLayoutX(16); // Sets the x location of gen7lbl2 to 16
		gen7lbl.setLayoutY(585); // Sets the y location of gen7lbl2 to 585
		gen7lbl.setFont(fontBold); // Sets the font of gen7lbl to bold
		gen7link.setLayoutX(54); // Sets the x location of gen7link2 to 54
		gen7link.setLayoutY(583); // Sets the y location of gen7link2 to 583
		gen8lbl.setLayoutX(16); // Sets the x location of gen8lbl2 to 16
		gen8lbl.setLayoutY(608); // Sets the y location of gen8lbl2 to 608
		gen8lbl.setFont(fontBold); // Sets the font of gen8lbl to bold
		gen8link.setLayoutX(54); // Sets the x location of gen8link2 to 54
		gen8link.setLayoutY(605); // Sets the y location of gen8link2 to 605
		 pokedexAnchor.getChildren().addAll(pokemonNameBox,pokemonSpriteView,shinyCheck,femaleCheck,formBox,pokemonNamelbl,cryButton,
				 genderRatiolbl,typesLabel,pokemonNolbl,classificationLabel,heightLabel,weightLabel,pkmnNameFillIn,genderRatioFillIn,type1PicView,
				 type2PicView,classFillIn,heightFillIn,weightFillIn,heightInchesFI,weightpoundsFI,captureRatelbl,baseEggStepslbl,catchRateFI,
				 eggCycleFI,expGrowthlbl,expGrowthFI,baseHappinesslbl,baseHapFI,evsEarnedlbl,evsEarnedFI,pokemonNoBox,baseEXPlbl,BaseEXPFI,
				 eggGroupslbl,eggGroup2,eggGroup1,pokedexTA, EvoTA,pokemonColorlbl,pokemonColorFI,abilitylbl,ability1FI,ability2FI,
				 hiddenAbilityFI,rockruffAbilityFI,competitivelbl,gen7lbl,gen7link,gen8lbl,gen8link); // Gets everything and adds them to the pokedexAnchor
		pokedexTab.setContent(pokedexAnchor); // Sets the contents of the Pokédex tab to pokedexAnchor
		abilityAnchor.setPrefHeight(180); // Sets the height of abilityAnchor to 180
		abilityAnchor.setPrefWidth(200); // Sets the width of abilityAnchor to 200
		abilityComboBox.setLayoutX(379); // Sets the x location of abilityComboBox to 379
		abilityComboBox.setLayoutY(4); // Sets the y location of abilityComboBox to 4
		abilityComboBox.setPrefWidth(150); // Sets the width of abilityComboBox to 150
		abilityNamelbl.setLayoutX(414); // Sets the x location of abilityNamelbl to 440
		abilityNamelbl.setLayoutY(33); // Sets the y location of abilityNamelbl to 33
		abilityNamelbl.setFont(fontBold); // Sets the font of abilityNamelbl to bold
		abilityNamelbl.setTooltip(new Tooltip("Name of the ability")); // Tooltip for abilitynamelbl
		abilityNameFI.setLayoutX(414); // Sets the x location of abilityNameFI to 426
		abilityNameFI.setLayoutY(51); // Sets the y location of abilityNameFI to 51
		gameTextlbl.setLayoutX(414); // Sets the x location of gameTextlbl to 422
		gameTextlbl.setLayoutY(142); // Sets the y location of gameTextlbl to 142
		gameTextlbl.setFont(fontBold); // Sets the font of gameTextlbl to bold
		gameTextlbl.setTooltip(new Tooltip("Ability text from the core series games")); // Tooltip explaining that the game text TextArea is
		gameTextFI.setLayoutX(15); // Sets the x location of gameTextFI to 15
		gameTextFI.setLayoutY(160); // Sets the y location of gameTextFI to 160
		gameTextFI.setPrefHeight(142); // Sets the height of gameTextFI to 142
		gameTextFI.setPrefWidth(875); // Sets the width of gameTextFI to 875
		gameTextFI.setWrapText(true); // Sets gameTextFi to have its text wrap around
		gameTextFI.setEditable(false); // Sets gameTextFI to NOT be editable
		inDepthEffectlbl.setLayoutX(414); // Sets the x location of inDepthEffectlbl to 414
		inDepthEffectlbl.setLayoutY(306); // Sets the y location of inDepthEffectlbl to 306
		inDepthEffectlbl.setFont(fontBold); // Sets the font of inDepthEffectlbl to bold
		/** Tooltip explaining what the in-depth TextArea talks about */
		inDepthEffectlbl.setTooltip(new Tooltip("Description that's more in-depth of what the ability does"));
		inDepthEffectFI.setLayoutX(15); // Sets the x location of inDepthEffectFI to 15
		inDepthEffectFI.setLayoutY(324); // Sets the y location of inDepthEffectFI to 324
		inDepthEffectFI.setPrefHeight(142); // Sets the height of inDepthEffectFI to 142
		inDepthEffectFI.setPrefWidth(875); // Sets the width of inDepthEffectFI to 875
		inDepthEffectFI.setWrapText(true); // Sets inDepthEffectFI to have its text wrap around
		inDepthEffectFI.setEditable(false); // Sets inDepthEffectFI to NOT be editable
		/** Gets everything and adds it to the abilityAnchor */
		abilityAnchor.getChildren().addAll(abilityComboBox,abilityNamelbl,abilityNameFI,gameTextlbl,gameTextFI,inDepthEffectlbl,inDepthEffectFI);
		abilityTab.setContent(abilityAnchor); // Sets the contents of the ability tab with abilityAnchor
		moveAnchor.setPrefHeight(180); // Sets the height of moveAnchor to 180
		moveAnchor.setPrefWidth(200); // Sets the width of moveAnchor to 200
		movesBox.setLayoutX(379); // Sets the x location of movesBox to 379
		movesBox.setLayoutY(4); // Sets the y location of movesBox to 4
		movesBox.setPrefWidth(150); // Sets the width of movesBox to 150
		moveNamelbl.setLayoutX(130); // Sets the x location of moveNamelbl to 131
		moveNamelbl.setLayoutY(30); // Sets the y location of moveNamelbl to 30
		moveNamelbl.setFont(fontBold); // Sets the font of moveNamelbl to bold
		moveNamelbl.setTooltip(new Tooltip("Name of the move")); // Tooltip for moveNamelbl
		moveNameFI.setLayoutX(130); // Sets the x location of moveNameFI to 117
		moveNameFI.setLayoutY(48); // Sets the y location of moveNameFI to 48
		moveTypelbl.setLayoutX(424); // Sets the x location of moveTypelbl to 424
		moveTypelbl.setLayoutY(30); // Sets the y location of moveTypelbl to 31
		moveTypelbl.setFont(fontBold); // Sets the font of moveTypelbl to bold
		moveTypelbl.setTooltip(new Tooltip("Type of the move")); // Tooltip for moveTypelbl
		moveTypePicView.setFitHeight(12); // Sets the height of moveTypePicView to 12
		moveTypePicView.setFitWidth(32); // Sets the width of moveTypePicView to 32
		moveTypePicView.setLayoutX(440); // Sets the x location of moveTypePicView to 437
		moveTypePicView.setLayoutY(51); // Sets the y location of moveTypePicView to 51
		categorylbl.setLayoutX(648); // Sets the x location of categorylbl to 648
		categorylbl.setLayoutY(30); // Sets the y location of categorylbl to 31
		categorylbl.setFont(fontBold); // Sets the font of categorylbl to bold
		categorylbl.setTooltip(new Tooltip("The category or kind of a move is an element of said move that determines the kind of damage it\n"
				+ "deals.")); // Tooltip explaining what a move category is
		categoryPicSmallView.setFitHeight(14); // Sets the height of categoryPicSmallView to 14
		categoryPicSmallView.setFitWidth(28); // Sets the width of categoryPicSmallView to 28
		categoryPicSmallView.setLayoutX(675); // Sets the x location of categoryPicSmallView to 658
		categoryPicSmallView.setLayoutY(50); // Sets the y location of categoryPicSmallView to 50
		categorypicView.setFitHeight(28); // Sets the height of categorypicView to 28
		categorypicView.setFitWidth(28); // Sets the width of categorypicView to 28
		categorypicView.setLayoutX(675); // Sets the x location of categorypicView to 658
		categorypicView.setLayoutY(50); // Sets the y location of categorypicView to 50
		powerPointslbl.setLayoutX(51); // Sets the x location of powerPointslbl to 51
		powerPointslbl.setLayoutY(88); // Sets the y location of powerPointslbl to 88
		powerPointslbl.setFont(fontBold); // Sets the font of powerPointslbl to bold
		powerPointslbl.setTooltip(new Tooltip("PP is an abbreviation of Power Points. Power Points measure the number of times a move can be used.\n"
				+ "The higher the PP, the more times you can use the move. PP are essential for attacking, supporting,\n"
				+ "stalling, recovering, and defending.")); // Tooltip explaining what Power Points are
		movePPFI.setLayoutX(78); // Sets the x location of movePPFI to 78
		movePPFI.setLayoutY(106); // Sets the y location of movePPFI to 106
		basePowerlbl.setLayoutX(311); // Sets the x location of basePowerlbl to 311
		basePowerlbl.setLayoutY(88); // Sets the y location of basePowerlbl to 88
		basePowerlbl.setFont(fontBold); // Sets the font of basePowerlbl to bold
		/** Tooltip explaining what a move's base power is */
		basePowerlbl.setTooltip(new Tooltip("Power is a property of moves that helps determine how much damage they deal."));
		moveBasePowerFI.setLayoutX(330); // Sets the x location of moveBasePowerFI to 330
		moveBasePowerFI.setLayoutY(106); // Sets the y location of moveBasePowerFI to 106
		accuracylbl.setLayoutX(548); // Sets the x location of accuracylbl to 548
		accuracylbl.setLayoutY(88); // Sets the y location of accuracylbl to 88
		accuracylbl.setFont(fontBold); // Sets the font of accuracylbl to bold
		/** Tooltip explaining what move accuracy is */
		accuracylbl.setTooltip(new Tooltip("Accuracy is an aspect of moves that, in conjunction with the user's in-battle accuracy stat and\n"
				+ "the target's evasion stat, determines how reliable they can hit their target."));
		moveAccuracyFI.setLayoutX(565); // Sets the x location of moveAccuracyFI to 559
		moveAccuracyFI.setLayoutY(106); // Sets the y location of moveAccuracyFI to 106
		speedPrioritylbl.setLayoutX(724); // Sets the x location of speedPrioritylbl to 724
		speedPrioritylbl.setLayoutY(88); // Sets the y location of speedPrioritylbl to 88
		speedPrioritylbl.setFont(fontBold); // Sets the font of speedPrioritylbl to bold
		/** Tooltip explaining what speed priority is */
		speedPrioritylbl.setTooltip(new Tooltip("Priority is a characteristic of moves, such that any move with a higher priority than another\n"
				+ "will always be performed first. When two moves have the same priority, the users' Speed\n"
				+ "statistics will determine which one is performed first in a battle."));
		speedPriorityFI.setLayoutX(753); // Sets the x location of speedPriorityFI to 753
		speedPriorityFI.setLayoutY(106); // Sets the y location of speedPriorityFI to 106
		battleEffectlbl.setLayoutX(14); // Sets the x location of battleEffectlbl to 14
		battleEffectlbl.setLayoutY(156); // Sets the y location of battleEffectlbl to 156
		battleEffectlbl.setFont(fontBold); // Sets the font of battleEffectlbl to bold
		/** Tooltip explaining what the first TextArea talks about */
		battleEffectlbl.setTooltip(new Tooltip("Move text from the core series games"));
		shortDescMoveFI.setLayoutX(17); // Sets the x location of shortDescMoveFI to 17
		shortDescMoveFI.setLayoutY(173); // Sets the y location of shortDescMoveFI to 173
		shortDescMoveFI.setPrefHeight(80); // Sets the height of shortDescMoveFI to 80
		shortDescMoveFI.setPrefWidth(880); // Sets the width of shortDescMoveFI to 880
		shortDescMoveFI.setWrapText(true); // Sets shortDescMoveFI to have its text wrap around
		shortDescMoveFI.setEditable(false); // Sets shortDescMoveFI to NOT be editable
		secondaryEffectlbl.setLayoutX(14); // Sets the x location of secondaryEffectlbl to 14
		secondaryEffectlbl.setLayoutY(255); // Sets the y location of secondaryEffectlbl to 255
		secondaryEffectlbl.setFont(fontBold); // Sets the font of secondaryEffectlbl to bold
		/** Tooltip explaining what the second TextArea talks about */
		secondaryEffectlbl.setTooltip(new Tooltip("Description that's more in-depth of what the move does"));
		inDepthDescMove.setLayoutX(17); // Sets the x location of inDepthDescMove to 17
		inDepthDescMove.setLayoutY(273); // Sets the y location of inDepthDescMove to 273
		inDepthDescMove.setPrefHeight(127); // Sets the height of inDepthDescMove to 127
		inDepthDescMove.setPrefWidth(880); // Sets the width of inDepthDescMove to 880
		inDepthDescMove.setWrapText(true); // Sets inDepthDescMove to have its text wrap around
		inDepthDescMove.setEditable(false); // Sets inDepthDescMove to NOT be editable
		/** Gets everything and adds it to the moveAnchor */
		moveAnchor.getChildren().addAll(movesBox,moveNamelbl,moveNameFI,moveTypelbl,moveTypePicView,categorylbl,categoryPicSmallView,
				categorypicView,powerPointslbl,movePPFI,basePowerlbl,moveBasePowerFI,accuracylbl,moveAccuracyFI,speedPrioritylbl,speedPriorityFI,
				battleEffectlbl,shortDescMoveFI,secondaryEffectlbl,inDepthDescMove);
		movesTab.setContent(moveAnchor); // Sets the contents of the moves Tab with moveAnchor
		statCalcAnchor.setPrefHeight(180); // Sets the height of statCalcAnchor to 180
		statCalcAnchor.setPrefWidth(200); // Sets the width of statCalcAnchor to 200
		hplbl.setLayoutX(327); // Sets the x location of hplbl to 332
		hplbl.setLayoutY(180); // Sets the y location of hplbl to 180
		hplbl.setFont(fontBold); // Sets the font of hplbl to bold
		hplbl.setTooltip(new Tooltip("The Hit Points, or HP for short in both Japanese and English, determine how much damage a Pokémon can\n"
				+ "receive before fainting. Many other game mechanics, such as Pokémon Centers, Substitute, Leftovers, and\n"
				+ "Pain Split, can decrease or restore HP. Lost HP is often not automatically restored at the end of a battle,\n"
				+ "so Pokémon can enter another battle without their full HP. ")); // Tooltip explaining the Hit Point stat
		attacklbl.setLayoutX(307); // Sets the x location of attacklbl to 313
		attacklbl.setLayoutY(207); // Sets the y location of attacklbl to 207
		attacklbl.setFont(fontBold); // Sets the font of attacklbl to bold
		attacklbl.setTooltip(new Tooltip("The Attack stat, or informally Physical Attack, partly determines how much damage a Pokémon deals\n"
				+ "when using a physical move.")); // Tooltip explaining the Attack stat
		defenselbl.setLayoutX(300); // Sets the x location to 304
		defenselbl.setLayoutY(234); // Sets the y location to 234
		defenselbl.setFont(fontBold); // Sets the font of defenselbl to bold
		defenselbl.setTooltip(new Tooltip("The Defense stat, or informally Physical Defense, partly determines how much damage a Pokémon\n"
				+ "receives when it is hit with a physical move.")); // Tooltip explaining the Defense stat
		spAtklbl.setLayoutX(265); // Sets the x location of spAtklbl to 273
		spAtklbl.setLayoutY(261); // Sets the y location of spAtklbl to 261
		spAtklbl.setFont(fontBold); // Sets the font of spAtklbl to bold
		spAtklbl.setTooltip(new Tooltip("The Special Attack stat, or Sp. Atk for short, partly determines how much damage a Pokémon deals when\n"
				+ "using a special move.")); // Tooltip explaining the Special Attack stat
		spDeflbl.setLayoutX(257); // Sets the x location of spDeflbl to 264
		spDeflbl.setLayoutY(288); // Sets the y location of spDeflbl to 288
		spDeflbl.setFont(fontBold); // Sets the font of spDeflbl to bold
		spDeflbl.setTooltip(new Tooltip("The Special Defense stat, or Sp. Def for short, partly determines how much damage a Pokémon receives\n"
				+ "when it is hit with a special move.")); // Tooltip explaining the Special Defense stat
		speedlbl.setLayoutX(310); // Sets the x location of speedlbl to 315
		speedlbl.setLayoutY(315); // Sets the y location of speedlbl to 315
		speedlbl.setFont(fontBold); // Sets the font of speedlbl to bold
		speedlbl.setTooltip(new Tooltip("The Speed stat determines the order of Pokémon that can act in battle. If Pokémon are moving with the\n"
				+ "same priority, Pokémon with higher Speed at the start of any turn will generally make a move before\n"
				+ "ones with lower Speed; in the case that two Pokémon have the same Speed, one of them will randomly\n"
				+ "go first.")); // Tooltip explaning the Speed stat
		baseHPTF.setEditable(false); // Sets baseHPTF to NOT be editable
		baseHPTF.setLayoutX(351); // Sets the x location of baseHPTF to 351
		baseHPTF.setLayoutY(176); // Sets the y location of baseHPTF to 176
		baseHPTF.setPrefHeight(26); // Sets the height of baseHPTF to 26
		baseHPTF.setPrefWidth(42); // Sets the width of baseHPTF to 42
		baseDefTF.setEditable(false); // Sets baseDefTF to NOT be editable
		baseDefTF.setLayoutX(351); // Sets the x location of baseDefTF to 351
		baseDefTF.setLayoutY(230); // Sets the y location of baseDefTF to 230
		baseDefTF.setPrefHeight(26); // Sets the height of baseDefTF to 26
		baseDefTF.setPrefWidth(42); // Sets the width of baseDefTF to 42
		baseAtkTF.setEditable(false); // Sets baseAtkTF to NOT be editable
		baseAtkTF.setLayoutX(351); // Sets the x location of baseAtkTF to 351
		baseAtkTF.setLayoutY(203); // Sets the y location of baseAtkTF to 203
		baseAtkTF.setPrefHeight(26); // Sets the height of baseAtkTF to 26
		baseAtkTF.setPrefWidth(42); // Sets the width of baseAtkTF to 42
		baseSpeTF.setEditable(false); // Sets baseSpeTFt to NOT be editable
		baseSpeTF.setLayoutX(351); // Sets the x location of baseSpeTF to 351
		baseSpeTF.setLayoutY(311); // Sets the y location of baseSpeTF to 311
		baseSpeTF.setPrefHeight(26); // Sets the height of baseSpeTF to 26
		baseSpeTF.setPrefWidth(42); // Sets the width of baseSpeTF to 42
		baseSpDefTF.setEditable(false); // Sets baseSpDefTF to NOT be editable
		baseSpDefTF.setLayoutX(351); // Sets the x location of baseSpDefTF to 351
		baseSpDefTF.setLayoutY(284); // Sets the y location of baseSpDefTF to 284
		baseSpDefTF.setPrefHeight(26); // Sets the height of baseSpDefTF to 26
		baseSpDefTF.setPrefWidth(42); // Sets the width of baseSpDef to 42
		baseSpAtkTF.setEditable(false); // Sets baseSpAtkTF to NOT be editable
		baseSpAtkTF.setLayoutX(351); // Sets the x location of baseSpAtkTF to 351
		baseSpAtkTF.setLayoutY(257); // Sets the y location of baseSpAtkTF to 257
		baseSpAtkTF.setPrefHeight(26); // Sets the height of baseSpAtkTF to 26
		baseSpAtkTF.setPrefWidth(42); // Sets the width of baseSpAtkTF to 42
		HPIVsTF.setLayoutX(400); // Sets the x location of HPIVsTF to 400
		HPIVsTF.setLayoutY(176); // Sets the y location of HPIVsTF to 176
		HPIVsTF.setPrefHeight(26); // Sets the height of HPIVsTF to 26
		HPIVsTF.setPrefWidth(42); // Sets the width of HPIVsTF to 42
		defIVsTF.setLayoutX(400); // Sets the x location of defIVsTF to 400
		defIVsTF.setLayoutY(230); // Sets the y location of defIVsTF to 230
		defIVsTF.setPrefHeight(26); // Sets the height of defIVsTF to 26
		defIVsTF.setPrefWidth(42); // Sets the width of defIVsTF to 42
		atkIVsTF.setLayoutX(400); // Sets the x location of atkIVsTF to 400
		atkIVsTF.setLayoutY(203); // Sets the y location of atkIVsTF to 203
		atkIVsTF.setPrefHeight(26); // Sets the height of atkIVsTF to 26
		atkIVsTF.setPrefWidth(42); // Sets the width of atkIVsTF to 42
		speIVsTF.setLayoutX(400); // Sets the x location of speIVsTF to 400
		speIVsTF.setLayoutY(311); // Sets the y location of speIVsTF to 311
		speIVsTF.setPrefHeight(26); // Sets the height of speIVsTF to 26
		speIVsTF.setPrefWidth(42); // Sets the width of speIVsTF to 42
		spDefIVsTF.setLayoutX(400); // Sets the x location of spDefIVsTF to 400
		spDefIVsTF.setLayoutY(284); // Sets the y location of spDefIVsTF to 284
		spDefIVsTF.setPrefHeight(26); // Sets the height of spDefIVs to 26
		spDefIVsTF.setPrefWidth(42); // Sets the width of spDefIVs to 42
		spAtkIVsTF.setLayoutX(400); // Sets the x location of spAtkIVsTF to 400
		spAtkIVsTF.setLayoutY(257); // Sets the y location of spAtkIVsTF to 257
		spAtkIVsTF.setPrefHeight(26); // Sets the height of spAtkIVsTF to 26
		spAtkIVsTF.setPrefWidth(42); // Sets the width of spAtkIVsTF to 42
		HPEVsTF.setLayoutX(449); // Sets the x location of HPEVsTF to 449
		HPEVsTF.setLayoutY(176); // Sets the y location of HPEVsTF to 176
		HPEVsTF.setPrefHeight(26); // Sets the height of HPEVsTF to 26
		HPEVsTF.setPrefWidth(42); // Sets the width of HPEVsTF to 42
		defEVsTF.setLayoutX(449); // Sets the x location of defEVsTF to 449
		defEVsTF.setLayoutY(230); // Sets the y location of defEVsTF to 230
		defEVsTF.setPrefHeight(26); // Sets the height of defEVsTF to 26
		defEVsTF.setPrefWidth(42); // Sets the width of defEVsTF to 42
		atkEVsTF.setLayoutX(449); // Sets the x location of atkEVsTF to 449
		atkEVsTF.setLayoutY(203); // Sets the y location of atkEVsTF to 203
		atkEVsTF.setPrefHeight(26); // Sets the height of atkEVsTF to 26
		atkEVsTF.setPrefWidth(42); // Sets the width of atkEVsTF to 42
		speEVsTF.setLayoutX(449); // Sets the x location of speEVsTF to 449
		speEVsTF.setLayoutY(311); // Sets the y location of speEVsTF to 311
		speEVsTF.setPrefHeight(26); // Sets the height of speEVsTF to 26
		speEVsTF.setPrefWidth(42); // Sets the width of speEVsTF to 42
		spDefEVsTF.setLayoutX(449); // Sets the x location of spDefEVsTF to 449
		spDefEVsTF.setLayoutY(284); // Sets the y location of spDefEVsTF to 284
		spDefEVsTF.setPrefHeight(26); // Sets the height of spDefEVsTF to 26
		spDefEVsTF.setPrefWidth(42); // Sets the width of spDefEVsTF to 42
		spAtkEVsTF.setLayoutX(449); // Sets the x location of spAtkEVsTF to 449
		spAtkEVsTF.setLayoutY(257); // Sets the y location of spAtkEVsTF to 257
		spAtkEVsTF.setPrefHeight(26); // Sets the height of spAtkEVsTF to 26
		spAtkEVsTF.setPrefWidth(42); // Sets the width of spAtkEVsTF to 42
		totalBaseStatsTF.setEditable(false); // Sets totalBaseStatsTF to NOT be editable
		totalBaseStatsTF.setLayoutX(351); // Sets the x location of totalBaseStatsTF to 351
		totalBaseStatsTF.setLayoutY(338); // Sets the y location of totalBaseStatsTF to 338
		totalBaseStatsTF.setPrefHeight(26); // Sets the height of totalBaseStatsTF to 26
		totalBaseStatsTF.setPrefWidth(42); // Sets the width of totalBaseStatsTF to 42
		totalIVsTF.setEditable(false); // Sets totalIVs TF to NOT be editable
		totalIVsTF.setLayoutX(400); // Sets the x location of totalIVsTF to 400
		totalIVsTF.setLayoutY(338); // Sets the y location of totalIVsTF to 338
		totalIVsTF.setPrefHeight(26); // Sets the height of totalIVsTF to 26
		totalIVsTF.setPrefWidth(42); // Sets the width of totalIVsTF to 42
		totalEVsTF.setEditable(false); // Sets totalEVsTF to NOT be editable
		totalEVsTF.setLayoutX(449); // Sets the x location of totalEVsTF to 449
		totalEVsTF.setLayoutY(338); // Sets the y location of totalEVsTF to 338
		totalEVsTF.setPrefHeight(26); // Sets the height of totalEVsTF to 26
		totalEVsTF.setPrefWidth(42); // Sets the width of totalEVsTF to 42
		hpStatTF.setEditable(false); // Sets hpStatTF to NOT be editable
		hpStatTF.setLayoutX(507); // Sets the x location of hpStatTF to 507
		hpStatTF.setLayoutY(176); // Sets the y location of hpStatTF to 176
		hpStatTF.setPrefHeight(26); // Sets the height of hpStatTF to 26
		hpStatTF.setPrefWidth(42); // Sets the width of hpStatTF to 42
		defStatTF.setEditable(false); // Sets defStatTF to NOT be editable
		defStatTF.setLayoutX(507); // Sets the x location of defStatTF to 507
		defStatTF.setLayoutY(230); // Sets the y location of defStatTF to 230
		defStatTF.setPrefHeight(26); // Sets the height of defStatTF to 26
		defStatTF.setPrefWidth(42); // Sets the width of defStatTF to 42
		atkStatTF.setEditable(false); // Sets atkStatTF to NOT be editable
		atkStatTF.setLayoutX(507); // Sets the x location of atkStatTF to 507
		atkStatTF.setLayoutY(203); // Sets the y location of atkStatTF to 203
		atkStatTF.setPrefHeight(26); // Sets the height of atkStatTF to 26
		atkStatTF.setPrefWidth(42); // Sets the width of atkStatTF to 42
		speStatTF.setEditable(false); // Sets speStatTF to NOT be editable
		speStatTF.setLayoutX(507); // Sets the x location of speStatTF to 507
		speStatTF.setLayoutY(311); // Sets the y location of speStatTF to 311
		speStatTF.setPrefHeight(26); // Sets the height of speStatTF to 26
		speStatTF.setPrefWidth(42); // Sets the width of speStatTF to 42
		spDefStatTF.setEditable(false); // Sets spDefStatTF to NOT be editable
		spDefStatTF.setLayoutX(507); // Sets the x location of spDefStatTF to 507
		spDefStatTF.setLayoutY(284); // Sets the y location of spDefStatTF to 284
		spDefStatTF.setPrefHeight(26); // Sets the height of spDefStatTF to 26
		spDefStatTF.setPrefWidth(42); // Sets the width of spDefStatTF to 42
		spAtkStatTF.setEditable(false); // Sets spAtkStatTF to NOT be editable
		spAtkStatTF.setLayoutX(507); // Sets the x location of spAtkStatTF to 507
		spAtkStatTF.setLayoutY(257); // Sets the y location of spAtkStatTF to 257
		spAtkStatTF.setPrefHeight(26); // Sets the height of spAtkStatTF to 26
		spAtkStatTF.setPrefWidth(42); // Sets the width of spAtkStatTF to 42
		totalStatsTF.setEditable(false); // Sets totalStatsTF to NOT be editable
		totalStatsTF.setLayoutX(507); // Sets the x location of totalStatsTF to 507
		totalStatsTF.setLayoutY(338); // Sets the y location of totalStatsTF to 338
		totalStatsTF.setPrefHeight(26); // Sets the height of totalStatsTF to 26
		totalStatsTF.setPrefWidth(42); // Sets the width of totalStatsTF to 42
		totallbl.setLayoutX(315); // Sets the x location of totallbl to 320
		totallbl.setLayoutY(342); // Sets the y location of totallbl to 342
		totallbl.setFont(fontBold); // Sets the font of totallbl to bold
		baselbl.setLayoutX(360); // Sets the x location of baselbl to 360
		baselbl.setLayoutY(158); // Sets the y location of baselbl to 158
		baselbl.setFont(fontBold); // Sets the font of baselbl to bold
		baselbl.setTooltip(new Tooltip("Species strengths, commonly referred to by fans as base stats, are the inherent values of a species or\n"
				+ "form of a species that are used to the stats of a Pokémon.")); // Tooltip explaining base stats
		ivslbl.setLayoutX(413); // Sets the x location of ivslbl to 413
		ivslbl.setLayoutY(158); // Sets the y location of ivslbl to 158
		ivslbl.setFont(fontBold); // Sets the font of ivslbl to bold
		ivslbl.setTooltip(new Tooltip("Individual strengths, abbreviated IVs from its more commonly known fan term, Individual values, are the\n"
				+ "Pokémon equivalent of genes. They are instrumental in determining the stats of a Pokémon, being\n"
				+ "responsible for the large variation in stats among untrained Pokémon of the same species.\n"
				+ "Lowest of 0, max of 31")); // Tooltips explaining Individual values
		evslbl.setLayoutX(460); // Sets the x location of evslbl to 460
		evslbl.setLayoutY(158); // Sets the y location of evslbl to 158
		evslbl.setFont(fontBold); // Sets the font of evslbl to bold
		evslbl.setTooltip(new Tooltip("Base points, commonly referred to by fans as effort values and abbreviated as EVs, are values that\n"
				+ "contribute to an individual Pokémon's stats in the core and side series Pokémon games. They are\n"
				+ "primarily obtained by defeating Pokémon in battle, based on the Pokémon that was defeated.\n"
				+ "Lowest of 0, highest of 252. EV total cannot be higher than 510")); // Tooltips explaining Effort Values
		statslbl.setLayoutX(515); // Sets the x location of statslbl to 515
		statslbl.setLayoutY(158); // Sets the y location of statslbl to 158
		statslbl.setFont(fontBold); // Sets the font of statslbl to bold
		/** Tooltip explaining stats */
		statslbl.setTooltip(new Tooltip("A statistic, or stat for short, is an element which determines certain aspects of battles in the\n"
				+ "games. Stats may also refer to the numerical values of each field in regards to individual Pokémon."));
		natureCombobox.setLayoutX(260); // Set the x location of natureCombobox to 260
		natureCombobox.setLayoutY(391); // Set the y location of natureCombobox to 391
		natureCombobox.setPrefWidth(150); // Set the width of natureCombobox to 150
		naturelbl.setLayoutX(264); // Set the x location of naturelbl to 264
		naturelbl.setLayoutY(374); // Set the y location of naturelbl to 374
		naturelbl.setFont(fontBold); // Sets the font of naturelbl to bold
		naturelbl.setTooltip(new Tooltip("Natures are a mechanic that influences how a Pokémon's stats grow. They were introduced in\n"
				+ "Generation III.")); // Tooltip explaining natures
		levelTF.setLayoutX(475); // Set the x location of levelTF to 475
		levelTF.setLayoutY(391); // Set the y location of levelTF to 391
		levellbl.setLayoutX(477); // Set the x location of levellbl to 477
		levellbl.setLayoutY(374); // Set the y location of levellbl to 374
		levellbl.setFont(fontBold); // Sets the font of levellbl to bold
		levellbl.setTooltip(new Tooltip("The level is a measurement of how strong a Pokémon currently is.\n"
				+ "Lowest level is 1, highest level is 100")); // Tooltip explaining levels
		getStatsButton.setLayoutX(410); // Set the x location of getStatsButton to 410
		getStatsButton.setLayoutY(424); // Set the y location of getStatsButton to 424
		getStatsButton.setVisible(false); // Sets getStatsButton to NOT be visible
		HPTypelbl.setLayoutX(560); // Sets the x location of HPTypelbl to 560
		HPTypelbl.setLayoutY(342); // Sets the y location of HPTypelbl to 342
		HPTypelbl.setFont(fontBold); // Sets the font of HPTypelbl to bold
		/** Tooltip explaining what Hidden Power type means */
		HPTypelbl.setTooltip(new Tooltip("In the Pokémon games, Hidden Power is a Normal-type move when the type is displayed, such as in\n"
				+ "battle and on status screens. However, the actual type of Hidden Power is determined by the\n"
				+ "Pokémon's individual values, and through calculation, can be set as one of other natural types."));
		HPImageView.setLayoutX(680); // Sets the x location of HPImageView to 680
		HPImageView.setLayoutY(345); // Sets the y location of HPImageView to 345
		/** Gets everything and alls it to the statCalcAnchor */
		statCalcAnchor.getChildren().addAll(hplbl,attacklbl,defenselbl,spAtklbl,spDeflbl,speedlbl,baseHPTF,baseAtkTF,baseDefTF,baseSpAtkTF,
				baseSpDefTF,baseSpeTF,HPIVsTF,atkIVsTF,defIVsTF,spAtkIVsTF,spDefIVsTF,speIVsTF,HPEVsTF,atkEVsTF,defEVsTF,spAtkEVsTF,spDefEVsTF,
				speEVsTF,totalBaseStatsTF,totalIVsTF,totalEVsTF,hpStatTF,atkStatTF,defStatTF,spAtkStatTF,spDefStatTF,speStatTF,totalStatsTF,
				totallbl,baselbl,ivslbl,evslbl,statslbl,natureCombobox,naturelbl,levelTF,levellbl,getStatsButton,HPTypelbl,HPImageView);
		statCalcTab.setContent(statCalcAnchor); // Sets the contents of the Stat Calculator tab with statCalcAnchor
		itemAnchor.setPrefHeight(180); // Sets the height of itemAnchor to 180
		itemAnchor.setPrefWidth(200); // Sets the width of itemAnchor to 200
		itemNameBox.setLayoutX(379); // Sets the x location of itemNameBox to 379
		itemNameBox.setLayoutY(4); // Sets the y location of itemNameBox to 4
		itemNameBox.setPrefWidth(150); // Sets the width of itemNameBox to 150
		itemNamelbl.setLayoutX(128); // Sets the x location of itemNamelbl to 128
		itemNamelbl.setLayoutY(41); // Sets the y location of itemNamelbl to 41
		itemNamelbl.setFont(fontBold); // Sets the font of itemNamelbl to bold
		itemNamelbl.setTooltip(new Tooltip("Name of the item")); // Tooltip saying what itemNamelbl is
		itemPocketlbl.setLayoutX(562); // Sets the x location of itemPocketlbl to 562
		itemPocketlbl.setLayoutY(41); // Sets the y location of itemPocketlbl to 41
		itemPocketlbl.setFont(fontBold); // Sets the font of itemPocketlbl to bold
		itemPocketlbl.setTooltip(new Tooltip("Pocket of where the item will go in your bag")); // Tooltip explaining what an item pocket it
		itemNameFI.setLayoutX(198); // Sets the x location of itemNameFI to 198
		itemNameFI.setLayoutY(41); // Sets the y location of itemNameFI to 41
		itemPocketFI.setLayoutX(636); // Sets the x location of itemPocketFI to 636
		itemPocketFI.setLayoutY(41); // Sets the y location of itemPocketFI to 41
		itemImageView.setFitHeight(36); // Sets the height of itemImageView to 36
		itemImageView.setFitWidth(36); // Sets the width of itemImageView to 36
		itemImageView.setLayoutX(436); // Sets the x location of itemImageView to 436
		itemImageView.setLayoutY(32); // Sets the y location of itemImageView to 32
		itemDescriptionlbl.setLayoutX(14); // Sets the x location of itemDescriptionlbl to 14
		itemDescriptionlbl.setLayoutY(68); // Sets the y location of itemDescriptionlbl to 68
		itemDescriptionlbl.setFont(fontBold); // Sets the font of itemDescriptionlbl to bold
		/** Tooltip explaining what the first TextArea does */
		itemDescriptionlbl.setTooltip(new Tooltip("Info on the item from the core series games"));
		itemDescTA.setLayoutX(14); // Sets the x location of itemDescTA to 14
		itemDescTA.setLayoutY(86); // Sets the y location of itemDescTA to 86
		itemDescTA.setPrefHeight(132); // Sets the height of itemDescTA to 132
		itemDescTA.setPrefWidth(878); // Sets the width of itemDescTA to 878
		itemDescTA.setWrapText(true); // Makes sure that itemDescTA's text is wrappable
		itemDescTA.setEditable(false); // Makes sure that itemDescTA is NOT editable
		itemDescriptionLongerlbl.setLayoutX(14); // Sets the x location of itemDescriptionLongerlbl to 14
		itemDescriptionLongerlbl.setLayoutY(242); // Sets the y location of itemDescriptionLongerlbl to 242
		itemDescriptionLongerlbl.setFont(fontBold); // Sets the font of itemDescriptionLongerlbl to bold
		/** Tooltip explaining what the second TextArea does */
		itemDescriptionLongerlbl.setTooltip(new Tooltip("In-depth description of what the item does"));
		itemDescLongerTA.setLayoutX(14); // Sets the x location of itemDescLongerTA to 14
		itemDescLongerTA.setLayoutY(260); // Sets the y location of itemDescLongerTA to 260
		itemDescLongerTA.setPrefHeight(132); // Sets the height of itemDescLongerTA to 132
		itemDescLongerTA.setPrefWidth(878); // Sets the width of itemDescLongerTA to 878
		itemDescLongerTA.setWrapText(true); // Makes sure that itemDescLongerTA's text is wrappable
		itemDescLongerTA.setEditable(false); // Makes sure that itemDescLongerTA is NOT editable
		tabpane.getTabs().addAll(pokedexTab,statCalcTab,abilityTab,movesTab,itemsTab); // Sets the tabs of the tabpane
		itemAnchor.getChildren().addAll(itemNameBox,itemNamelbl,itemPocketlbl,itemNameFI,itemPocketFI,itemImageView,itemDescriptionlbl,
				itemDescTA,itemDescriptionLongerlbl,itemDescLongerTA); // Adds everything to the itemAnchor
		itemsTab.setContent(itemAnchor); // Puts itemAnchor into itemsTab
		mainAnchorPane.getChildren().add(tabpane); // Gets the tabpane and adds it to the mainAnchorPane
		vbox.getChildren().addAll(menubar,mainAnchorPane); // Gets the menubar and mainAnchorPane and adds it to the vbox
		loginAnchor.setMaxHeight(206); // Sets the max height of loginAnchor to 206
		loginAnchor.setMaxWidth(454); // Sets the max width of loginAnchor to 454
		loginAnchor.setMinHeight(100); // Sets the mid height of loginAnchor to 100
		loginAnchor.setMinWidth(100); // Sets the mid width of loginAnchor to 100
		loginAnchor.setPrefHeight(206); // Sets the pref height of loginAnchor to 206
		loginAnchor.setPrefWidth(258); // Sets the pref width of loginAnchor to 258
		welcomeTolbl.setLayoutX(10); // Sets the x location of welcomeTolbl to 20
		welcomeTolbl.setLayoutY(14); // Sets the y location of welcomeTolbl to 14
		welcomeTolbl.setFont(new Font("System Bold",20)); // Sets the font of welcomeTolbl to System Bold 20 size
		pleaseEnterlbl.setLayoutX(44); // Sets the x location of pleaseEnterlbl to 44
		pleaseEnterlbl.setLayoutY(46); // Sets the y location of pleaseEnterlbl to 46
		usernamelbl.setLayoutX(20); // Sets the x location of usernamelbl to 20
		usernamelbl.setLayoutY(69); // Sets the y location of usernamelbl to 69
		passwordlbl.setLayoutX(20); // Sets the x location of passwordlbl to 20
		passwordlbl.setLayoutY(101); // Sets the y location of passwordlbl to 101
		usernameFI.setLayoutX(81); // Sets the x location of usernameFI to 81
		usernameFI.setLayoutY(65); // Sets the y location of usernameFI to 65
		passwordFI.setLayoutX(81); // Sets the x location of passwordFI to 81
		passwordFI.setLayoutY(94); // Sets the y location of passwordFI to 94
		newHerelbl.setLayoutX(42); // Sets the x location of newHerelbl to 42
		newHerelbl.setLayoutY(165); // Sets the y location of newHerelbl to 165
		loginbtn.setLayoutX(72); // Sets the x location of loginbtn to 72
		loginbtn.setLayoutY(132); // Sets the y location of loginbtn to 132
		cancelbtn.setLayoutX(122); // Sets the x location of cancelbtn to 122
		cancelbtn.setLayoutY(132); // Sets the y location of cancelbtn to 132
		createAccountHL.setLayoutX(96); // Sets the x location of createAccountHL to 96
		createAccountHL.setLayoutY(163); // Sets the y location of createAccountHL to 163
		loginAnchor.getChildren().addAll(welcomeTolbl,pleaseEnterlbl,usernamelbl,passwordlbl,usernameFI,passwordFI,newHerelbl,loginbtn,cancelbtn,
				createAccountHL); // Adds everything to the loginAnchorPane
		login.getChildren().add(loginAnchor); // Places the AnchorPane into the VBox
		loginStage.setTitle("Login"); // Sets the title to Login
		loginStage.setScene(loginScene); // Sets the stage to the login scene
		loginStage.show(); // loginStage activate
		loginStage.setResizable(false); // makes sure that the window cannot be resized
		createAccount.setPrefHeight(250); // Sets the height of createAccount to 250
		createAnchor.setPrefHeight(206); // Set the height of createAnchor to 206
		createAnchor.setPrefWidth(250); // Sets the width of createAnchor of 250
		createAccountlbl.setLayoutX(40); // Sets the x location of createAccountlbl to 40
		createAccountlbl.setLayoutY(14); // Sets the y location of createAccountlbl to 14
		createAccountlbl.setFont(new Font("System Bold",20)); // Sets the font of createAccountlbl to System Bold and 20 size font
		firstNamelbl.setLayoutX(14); // Sets the x location of firstNamelbl to 14
		firstNamelbl.setLayoutY(60); // Sets the y location of firstNamelbl to 60
		firstNameFI.setLayoutX(81); // Sets the x location of firstNameFI to 81
		firstNameFI.setLayoutY(56); // Sets the y location of firstNameFI to 56
		lastNamelbl.setLayoutX(14); // Sets the x location of lastNamelbl to 14
		lastNamelbl.setLayoutY(98); // Sets the y location of lastNamelbl to 98
		lastNameFI.setLayoutX(81); // Sets the x location of lastNameFI to 81
		lastNameFI.setLayoutY(94); // Sets the y location of lastNameFI to 94
		createUNlbl.setLayoutX(14); // Sets the x location of createUNlbl to 14
		createUNlbl.setLayoutY(135); // Sets the y location of createUNlbl to 135
		createUNFI.setLayoutX(81); // Sets the x location of createUNFI to 81
		createUNFI.setLayoutY(131); // Sets the y location of createUNFI to 131
		createPWlbl.setLayoutX(14); // Sets the x location of createPWlbl to 14
		createPWlbl.setLayoutY(171); // Sets the y location of createPWlbl to 171
		createPWFI.setLayoutX(81); // Sets the x location of createPWFI to 81
		createPWFI.setLayoutY(167); // Sets the y location of createPWFI to 167
		createAccountbtn.setLayoutX(43); // Sets the x location of createAccountbtn to 43
		createAccountbtn.setLayoutY(209); // Sets the y location of createAccountbtn to 209
		cancelbtnCA.setLayoutX(153); // Sets the x location of cancelbtnCA to 153
		cancelbtnCA.setLayoutY(209); // Sets the y location of cancelbtnCA to 209
		createAnchor.getChildren().addAll(createAccountlbl,firstNamelbl,firstNameFI,lastNamelbl,lastNameFI,createUNlbl,createUNFI,createPWlbl,
				createPWFI,createAccountbtn,cancelbtnCA); // Adds everything to the createAnchor
		createAccount.getChildren().add(createAnchor); // Adds everything to the createAccount VBox
		loginStage.getIcons().add(new Image("icon/pokeballIcon.png"));
		createStage.getIcons().add(new Image("icon/pokeballIcon.png"));
		
		pokemonNameBox.setOnAction(e -> { // Event that starts when a user selects a Pokémon from the ComboBox.
			getPokemon(); // Calls the pokemonNameBox
		}); // end of pokemonNameBox event
		
		shinyCheck.setOnAction(checkBoxes); // Sets the action of the shinyCheck CheckBox to checkboxes EventHandler
		femaleCheck.setOnAction(checkBoxes); // Sets the action of the femaleCheck CheckBox to checkboxes EventHandler
		
		abilityComboBox.setOnAction(e -> { // Event that starts when a user select a new ability
			getAbility(); // Calls to the getAbility method
		}); // End of abilityComboBox event
		
		itemNameBox.setOnAction(e -> { // Event that starts when a user selects a new item
			getItem(); // Calls to the getItem method
		}); // End of itemNameBox
		
		movesBox.setOnAction(e -> { // Event that starts when a user selects a new move
			getMove(); // Calls to the getMove method
		}); // End of movesBox event
		
		pokemonNoBox.setOnAction(e -> { // Event that starts when a user inputs a NUMBER
			getPokemonNo(); // Calls to the getPokemonNo method
		}); // End of pokemonNoBox event
		
		cryButton.setOnAction(e -> { // Event when the user presses the button, it plays the cry of the Pokémon
			cryPlayer.play(); // Plays the cry of the Pokémon
		}); // End of cryButton event
		
		getStatsButton.setOnAction(e -> { // Event that calculates the stats of a Pokémon
			statCalculator(); // Method that calcuates the stats
		}); // End getStatsButton event
		
		formBox.setOnAction(e -> { // Event that selects a form of a Pokémon, if they have one
			getForm(); // Calls to the getForm method
			}); // End of formBox event
		
		createStage.setOnCloseRequest(e -> { // Event that closes createStage with the X button
			createStage.close(); // Closes the createStage stage
			usernameFI.clear(); // Clears username field
			passwordFI.clear(); // Clears password field
			loginStage.show(); // Opens the loginStage scene
		}); // End of createStage close event
		
		loginbtn.setOnAction(e -> {
			Login(); // Calls to the Login method
		}); // End loginbtn event
		
		cancelbtn.setOnAction(e -> { // closes the login page and closes the program
			loginStage.close(); // Closes loginStage
			try { // Try block to close connection
				connection.close(); // closes database connection
			} catch (SQLException e1) { // Catch block for try, should not happen
			} // End of catch
		}); // Ends cancelbtn event
		
		createAccountHL.setOnAction(e -> { // Hyperlink where the user can create an account
			openCAWindow(); // Calls to the openCAWindow method
		}); // Ends createAccount event
		
		createAccountbtn.setOnAction(e -> {
			CreateAccount(); // Calls to the CreateAccount method
		}); // End createAccountbtn event
		
		cancelbtnCA.setOnAction(e -> { // Cancels the create account page and reopens the login screen
			createStage.close(); // Closes createStage stage
			usernameFI.clear(); // Clears username field
			passwordFI.clear(); // Clears password field
			loginStage.show(); // Opens loginStage stage
		}); // Ends cancelbtnCA event
		
		menuitem1.setOnAction(e -> {
			saveDexPage(); // Calls to the saveDexPage method
		}); // End of menuitem1 event
		
		menuitem4.setOnAction(e -> {
			clearDexPage(); // Calls to the clearDexPage method
		}); // End menuitem4 event
		
		gen7link.setOnAction( e -> { // Hyperlink event for the gen 7 link in the abilities tab
			openGen7Link(); // Goes to the openGen7Link() method
		}); // ends gen7link event
		
		gen8link.setOnAction( e -> { // Hyperlink event for the gen 8 link in the abilities tab
			openGen8Link(); // Goes to the openGen8Link() method
		}); // Ends gen8link event
		
		menuitem2.setOnAction( e-> { // Event that closes the program
			mainStage.close();
			try { // Try block for connection
				connection.close(); // Closes database connection
			} catch (SQLException e1) { // Catch block, should not happen
				e1.printStackTrace();
			} // End of catch
		}); // Ends menuitem2 event
		
		menuitem3.setOnAction( e -> { // Event that opens the document
			openDocument(); // Goes to the openDocument() method
		}); // Ends menuitem3 event
		
		menuitem5.setOnAction(e -> { // Event that open the about section
			openAbout(); // Goes to the openAbout() method
		}); // Ends menuitem5 event
		
		menuitem6.setOnAction(e -> {
			openCredits();
		});
	} // End of start method
	
	/** Method that shows Credits for the program */
	private void openCredits() {
		final Stage credits = new Stage(); // Create a new stage called credits
		credits.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
		credits.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
        VBox creditvbox = new VBox(); // Create a VBox called creditvbox
        AnchorPane creditsAnchor = new AnchorPane(); // Creates an AnchorPane called creditsAnchor
        ImageView bulbapediaLogo = new ImageView(new Image("logos/Bulbapedia.png")); // Imageview that holds Bulbapedia's logo
        bulbapediaLogo.setFitHeight(50); // Sets the height of bulbapediaLogo to 50
        bulbapediaLogo.setFitWidth(50); // Sets the width of bulbapediaLogo to 50
        bulbapediaLogo.setOpacity(0.5); // Sets the opacity of bulbapediaLogo to 50%
        bulbapediaLogo.setLayoutX(10); // Sets the x location of bulbapediaLogo to 10
        bulbapediaLogo.setLayoutY(10); // Sets the y location of bulbapediaLogo to 10
        ImageView serebiiLogo = new ImageView(new Image("logos/serebii.png")); // ImageView that holds Serebii's "logo"
        serebiiLogo.setFitHeight(50); // Sets the height of serebiiLogo to 50
        serebiiLogo.setFitWidth(50); // Sets the width of serebiiLogo to 50
        serebiiLogo.setOpacity(0.5); // Sets the opacity of serebiiLogo to 50%
        serebiiLogo.setLayoutX(350); // Sets the x location of serebiiLogo to 350
        serebiiLogo.setLayoutY(10); // Sets the y location of serebiiLogo to 10
        ImageView nintendoLogo = new ImageView(new Image("logos/nintendo.png")); // ImageView that holds Nintendo's logo
        nintendoLogo.setFitHeight(35); // Sets the height of nintendoLogo to 35
        nintendoLogo.setFitWidth(85); // Sets the width of nintendoLogo to 85
        nintendoLogo.setOpacity(0.5); // Sets the opacity of nintendoLogo to 50%
        nintendoLogo.setLayoutX(1); // Sets the x location of nintendoLogo to 1
        nintendoLogo.setLayoutY(215); // Sets the y location of nintendoLogo to 215
        ImageView pokemonCompanyLogo = new ImageView(new Image("logos/PokemonCompany.png")); // Imageview that holds The Pokémon Company's logo
        pokemonCompanyLogo.setFitHeight(25); // Sets the height of pokemonCompanyLogo to 25
        pokemonCompanyLogo.setFitWidth(100); // Sets the width of pokemonCompanyLogo to 100
        pokemonCompanyLogo.setOpacity(0.5); // Sets the opacity of pokemonCompanyLogo to 50%
        pokemonCompanyLogo.setLayoutX(285); // Sets the x location of pokemonCompanyLogo to 285
        pokemonCompanyLogo.setLayoutY(215); // Sets the y location of pokemonCompanyLogo to 215
        ImageView gameFreakLogo = new ImageView(new Image("logos/GameFreak.png")); // ImageView that hold's Game Freak's Logo
        gameFreakLogo.setFitHeight(50); // Sets the height of gameFreaklogo to 50
        gameFreakLogo.setFitWidth(75); // Sets the width of gameFreaklogo to 75
        gameFreakLogo.setOpacity(0.5); // Sets the opacity of gameFreaklogo to 50%
        gameFreakLogo.setLayoutX(160); // Sets the x location of gameFreaklogo to 160
        gameFreakLogo.setLayoutY(85); // Sets the y location of gameFreaklogo to 85
        Button okButton = new Button("OK"); // Creates a new button called okButton
        okButton.setLayoutX(175); // Sets the x location of okButton to 175
        okButton.setLayoutY(200); // Sets the y location of okButton to 200
        Label oklbl = new Label("Bulbapedia and Serebii for their pages about the\nPokémon\n\n\n\n\n\n\n\nAll rights go to "
        		+ "Game Freak/Nintendo/The Pokémon\nCompany"); // Label
        oklbl.setFont(fontBold); // Sets the font of oklbl to bold
        oklbl.setLayoutX(60); // Sets the x location of oklbl to 60
        oklbl.setLayoutY(14); // Sets the y location of oklbl to 14
        /** Adds everything to the creditsAnchor */
        creditsAnchor.getChildren().addAll(okButton,oklbl,bulbapediaLogo,serebiiLogo,nintendoLogo,pokemonCompanyLogo,gameFreakLogo);
        creditvbox.getChildren().add(creditsAnchor); // Adds everything to the creditvbox
        Scene dialogScene = new Scene(creditvbox, 400, 250); // Creates a new scene called dialogScene
        credits.setScene(dialogScene); // Sets the dialogScene scene to credits stage
        credits.setTitle("Credits"); // Sets the title of credits
        credits.getIcons().add(new Image("icon/pokeballIcon.png"));
        credits.show(); // Shows credits stage
        credits.setResizable(false); // Makes sure that credits can't be resized
        okButton.setOnAction(e2 -> { // Event that closes credits
        	credits.close(); // Closes credits
        }); // End okButton event
	} // End of method
	
	private void openAbout() {
		final Stage about = new Stage(); // Create a new stage called ohno
		about.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
		about.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
        VBox aboutvbox = new VBox(); // Create a VBox called ohnovbox
        AnchorPane aboutAnchor = new AnchorPane(); // Creates an AnchorPane called ohnoAnchor
        Button okButton = new Button("OK"); // Creates a new button called okButton
        okButton.setLayoutX(135); // Sets the x location of okButton to 81
        okButton.setLayoutY(100); // Sets the y location of okButton to 56
        Label oklbl = new Label("This is a program meant for people who want to learn\nabout a specific Pokémon, and learn about more than\n"
        		+ "just their stats. Also, this is a product used to learn\nabout a specific move, ability, or item.");
        oklbl.setLayoutX(10); // Sets the x location of oklbl to 32
        oklbl.setLayoutY(14); // Sets the y location of oklbl to 14
        aboutAnchor.getChildren().addAll(okButton,oklbl); // Adds everything to the ohnoAnchor
        aboutvbox.getChildren().add(aboutAnchor); // Adds everything to the ohnovbox
        Scene dialogScene = new Scene(aboutvbox, 300, 150); // Creates a new scene called dialogScene
        about.setScene(dialogScene); // Sets the dialogScene scene to ohno stage
        about.setTitle("About");
        about.getIcons().add(new Image("icon/pokeballIcon.png"));
        about.show(); // Shows ohno stage
        about.setResizable(false); // Makes sure that ohno can't be resized
        okButton.setOnAction(e2 -> { // Event that closes ohno
        	about.close(); // Closes ohno
        }); // End okButton event
	}
	/** Method that leads to the online document */
	private void openDocument() {
		getHostServices().showDocument("https://1drv.ms/w/s!ApaLfDgCi8U2giaLD7svvLeMmF63?e=nQe1sL");
	}
	/** Method that calculates the stats of a Pokémon */
	public void statCalculator() {
		attacklbl.setTextFill(Color.web("#000000")); // Sets the color of attacklbl to black
		defenselbl.setTextFill(Color.web("#000000")); // Sets the color of defenselbl to black
		spAtklbl.setTextFill(Color.web("#000000")); // Sets the color of spAtklbl to black
		spDeflbl.setTextFill(Color.web("#000000")); // Sets the color of spDeflbl to black
		speedlbl.setTextFill(Color.web("#000000")); // Sets the color of speedlbl to black
		try { // try block for the whole event, and to make sure no errors occur
			/** If the user inputs a level higher than 100 or lower than 1..., it says no you can't do that */
			if (Integer.parseInt(levelTF.getText()) >= 101 || Integer.parseInt(levelTF.getText()) <= 0) {
				final Stage levelProb = new Stage(); // Create a stage called levelProb
			    levelProb.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
			    levelProb.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
			    VBox dialogVbox = new VBox(20); // Create a VBox called dialogVbox
			    /** Adds everything to the dialogVbox */
			    dialogVbox.getChildren().add(new Text("Level cannot be higher than 100 or lower than 1"));
			    Scene dialogScene = new Scene(dialogVbox, 260, 15); // Creates a new scene called dialogScene
			    levelProb.setScene(dialogScene); // Sets the dialogScene to the levelProb stage
			    levelProb.getIcons().add(new Image("icon/pokeballIcon.png"));
			    levelProb.show(); // Shows the levelProb stage
			} // End if
			/** If the user input an IV value higher than 31 or lower than 0, it says that you can't do that */
			else if (Integer.parseInt(HPIVsTF.getText()) >= 32 || Integer.parseInt(HPIVsTF.getText()) <= -1 || 
					Integer.parseInt(atkIVsTF.getText()) >= 32 || Integer.parseInt(atkIVsTF.getText()) <= -1 ||
					Integer.parseInt(defIVsTF.getText()) >= 32 || Integer.parseInt(defIVsTF.getText()) <= -1 ||
					Integer.parseInt(spAtkIVsTF.getText()) >= 32 || Integer.parseInt(spAtkIVsTF.getText()) <= -1 ||
					Integer.parseInt(spDefIVsTF.getText()) >= 32 || Integer.parseInt(spDefIVsTF.getText()) <= -1 ||
					Integer.parseInt(speIVsTF.getText()) >= 32 || Integer.parseInt(speIVsTF.getText()) <= -1)  {
				final Stage IVProb = new Stage(); // Create a stage called IVProb
				IVProb.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
				IVProb.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
			    VBox dialogVbox = new VBox(20); // Create a VBox called dialogVbox
			    /** Adds everything to the dialogVbox */
			    dialogVbox.getChildren().add(new Text("IVs cannot be higher than 31 or lower than 0"));
			    Scene dialogScene = new Scene(dialogVbox, 240, 20); // Create a new scene called dialogScene
			    IVProb.setScene(dialogScene); // Sets the dialogScene to the IVProb stage
			    IVProb.getIcons().add(new Image("icon/pokeballIcon.png"));
			    IVProb.show(); // Shows the IVProb stage
			} // End else if
			/** If the user input an EV value higher than 252 or lower than 0, it says you can't do that */
			else if (Integer.parseInt(HPEVsTF.getText()) >= 253 || Integer.parseInt(HPEVsTF.getText()) <= -1 ||
					Integer.parseInt(atkEVsTF.getText()) >= 253 || Integer.parseInt(atkEVsTF.getText()) <= -1 ||
					Integer.parseInt(defEVsTF.getText()) >= 253 || Integer.parseInt(defEVsTF.getText()) <= -1 ||
					Integer.parseInt(spAtkEVsTF.getText()) >= 253 || Integer.parseInt(spAtkEVsTF.getText()) <= -1 ||
					Integer.parseInt(spDefEVsTF.getText()) >= 253 || Integer.parseInt(spDefEVsTF.getText()) <= -1 ||
					Integer.parseInt(speEVsTF.getText()) >= 253 || Integer.parseInt(speEVsTF.getText()) <= -1) {
				final Stage EVProb = new Stage(); // Create a stage called EVProb
				EVProb.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
				EVProb.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
			    VBox dialogVbox = new VBox(20); // Create a VBox called dialogVbox
			    /** Adds everything to the dialogVbox */
			    dialogVbox.getChildren().add(new Text("EVs cannot be higher than 252 or lower than 0"));
			    Scene dialogScene = new Scene(dialogVbox, 250, 20); // Create a new scene called dialogScene
			    EVProb.setScene(dialogScene); // Sets the dialogScene to the EVProb stage
			    EVProb.getIcons().add(new Image("icon/pokeballIcon.png"));
			    EVProb.show(); // Shows the EVProb stage
			} // end else if
			else { // Else...
				IVs[0] = Integer.parseInt(HPIVsTF.getText()); // Sets IVs[0] from HPIVsTF, converted from string to int
				IVs[1] = Integer.parseInt(atkIVsTF.getText()); // Sets IVs[1] from atkIVsTF, converted from string to int
				IVs[2] = Integer.parseInt(defIVsTF.getText()); // Sets IVs[2] from defIVsTF, converted from string to int
				IVs[3] = Integer.parseInt(spAtkIVsTF.getText()); // Sets IVs[3] from spAtkIVsTF, converted from string to int
				IVs[4] = Integer.parseInt(spDefIVsTF.getText()); // Sets IVs[4] from spDefIVsTF, converted from string to int
				IVs[5] = Integer.parseInt(speIVsTF.getText()); // Sets IVs[5] from speIVsTF, converted from string to int
				EVs[0] = Integer.parseInt(HPEVsTF.getText()); // Sets EVs[0] from HPEVsTF, converted from string to int
				EVs[1] = Integer.parseInt(atkEVsTF.getText()); // Sets EVs[1] from atkEVsTF, converted from string to int
				EVs[2] = Integer.parseInt(defEVsTF.getText()); // Sets EVs[2] from defEVsTF, converted from string to int
				EVs[3] = Integer.parseInt(spAtkEVsTF.getText()); // Sets EVs[3] from spAtkEVsTF, converted from string to int
				EVs[4] = Integer.parseInt(spDefEVsTF.getText()); // Sets EVs[4] from spDefEVsTF, converted from string to int
				EVs[5] = Integer.parseInt(speEVsTF.getText()); // Sets EVs[5] from speEVsTF, converted from string to int
				pokemonLevel = Integer.parseInt(levelTF.getText()); // Sets the level from the level TextField
				pokemonNature = natureCombobox.getValue(); // Sets the nature from the nature ComboBox
				if (baseStats[0] == 1) { // If the Pokémon's base HP is 1...
					realStats[0] = 1; // The HP stat will always be 1, this is meant for Shedinja
				} // End if
				else { // Else...
					/** This is the calcuation to find the HP stat */
					realStats[0] = (((2 * baseStats[0] + IVs[0] + (EVs[0] / 4)) * pokemonLevel) / 100) + pokemonLevel + 10;
				} // end else
				for (int i = 1; i < baseStats.length; i++) { // For loop to calculate the other stats
					/** Equation that calculates the other stats */
					realStats[i] = (((2 * baseStats[i] + IVs[i] + (EVs[i] / 4)) * pokemonLevel) / 100) + 5;
					} // End For loop
				EVTotal = 0; // Resets EVTotal to 0
				for (int i = 0; i < EVs.length; i++) { // loop to find the sum of all the EVs
					EVTotal += EVs[i]; // Adds whatever EVs are in that slot to EVTotal
				} // End For loop
				if (EVTotal >= 511) { // If the EV total is greater than 510..., a popup says that can't happen
					final Stage tooManyEVs = new Stage(); // Creates a new stage called tooManyEVs
				    tooManyEVs.initModality(Modality.APPLICATION_MODAL); // // Specifies the modality for this stage.
				    tooManyEVs.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
				    VBox dialogVbox = new VBox(20); // Creates a VBox called dialogVbox
				    dialogVbox.getChildren().add(new Text("EV total cannot exceed 510")); // Adds everything to dialogVbox
				    Scene dialogScene = new Scene(dialogVbox, 150, 20); // Creates a new scene called dialogScene
				    tooManyEVs.setScene(dialogScene); // Sets the dialogScene to the tooManyEVs stage
				    tooManyEVs.getIcons().add(new Image("icon/pokeballIcon.png"));
				    tooManyEVs.show(); // shows the tooManyEVs stage
				} // End if
				else { // Else...
					totalEVsTF.setText(Integer.toString(EVTotal)); // Sets the text of totalEVsTF with EVTotal
					/** If the nature is either Lonely, Brave, Adamant, or Naughty...*/
					if (pokemonNature == "Lonely" || pokemonNature == "Brave" || pokemonNature == "Adamant" || pokemonNature == "Naughty")
						realStats[1] += (realStats[1] * .1); // Gives a 10% boost to the Pokémon's Attack stat
					/** If the nature is either Bold, Timid, Modest, or Calm...*/
					if (pokemonNature == "Bold" || pokemonNature == "Timid" || pokemonNature == "Modest" || pokemonNature == "Calm")
						realStats[1] -= (realStats[1] * .1); // Gives a 10% reduction to the Pokémon's Attack stat
					/** If the nature is either Bold, Relaxed, Impish, or Lax...*/
					if (pokemonNature == "Bold" || pokemonNature == "Relaxed" || pokemonNature == "Impish" || pokemonNature == "Lax")
						realStats[2] += (realStats[2] * .1); // Gives a 10% boost to the Pokémon's Defense stat
					/** If the nature is either Lonely, Hasty, Mild, or Gentle...*/
					if (pokemonNature == "Lonely" || pokemonNature == "Hasty" || pokemonNature == "Mild" || pokemonNature == "Gentle")
						realStats[2] -= (realStats[2] * .1); // Gives a 10% reduction to the Pokémon's Defense stat
					/** If the nature is either Modest, Mild, Quiet, or Rash...*/
					if (pokemonNature == "Modest" || pokemonNature == "Mild" || pokemonNature == "Quiet" || pokemonNature == "Rash")
						realStats[3] += (realStats[3] * .1); // Gives a 10% boost to the Pokémon's Special Attack stat
					/** If the nature is either Adamant, Impish, Jolly, or Careful... */
					if (pokemonNature == "Adamant" || pokemonNature == "Impish" || pokemonNature == "Jolly" || pokemonNature == "Careful")
						realStats[3] -= (realStats[3] * .1); // Gives a 10% reduction to the Pokémon's Special Attack stat
					/** If the nature is either Calm, Gentle, Sassy, or Careful... */
					if (pokemonNature == "Calm" || pokemonNature == "Gentle" || pokemonNature == "Sassy" || pokemonNature == "Careful")
						realStats[4] += (realStats[4] * .1); // Gives a 10% boost to the Pokémon's Special Defense stat
					/** If the nature is either Naughty, Lax, Naive, or Rash... */
					if (pokemonNature == "Naughty" || pokemonNature == "Lax" || pokemonNature == "Naive" || pokemonNature == "Rash")
						realStats[4] -= (realStats[4] * .1); // Gives a 10% reduction to the Pokémon's Special Defense stat
					/** If the nature is either Timid, Hasty, Jolly, or Naive... */
					if (pokemonNature == "Timid" || pokemonNature == "Hasty" || pokemonNature == "Jolly" || pokemonNature == "Naive")
						realStats[5] += (realStats[5] * .1); // Gives a 10% boost to the Pokémon's Speed stat
					/** If the nature is either Brave, Relaxed, Quiet, or Sassy... */
					if (pokemonNature == "Brave" || pokemonNature == "Relaxed" || pokemonNature == "Quiet" || pokemonNature == "Sassy")
						realStats[5] -= (realStats[5] * .1); // Gives a 10% reduction to the Pokémon's Speed stat
					switch(pokemonNature) { // Switch statement for natures
						case "Lonely": // if the nature is Lonely
							attacklbl.setTextFill(Color.web("#FF0000")); // Sets the color of attacklbl to Red
							defenselbl.setTextFill(Color.web("#0000FF")); // Sets the color of defenselbl to Blue
							break; // Break to prevent conflicts
						case "Brave": // if the nature is Brave
							attacklbl.setTextFill(Color.web("#FF0000")); // Sets the color of attacklbl to Red
							speedlbl.setTextFill(Color.web("#0000FF")); // Sets the color of speedlbl to Blue
							break; // Break to prevent conflicts
						case "Adamant": // If the nature is Adamant
							attacklbl.setTextFill(Color.web("#FF0000")); // Sets the color of attacklbl to Red
							spAtklbl.setTextFill(Color.web("#0000FF")); // Sets the color of spAtk to Blue
							break; // Break to prevent conflicts
						case "Naughty": // If the nature is Naughty
							attacklbl.setTextFill(Color.web("#FF0000")); // Sets the color of attacklbl to Red
							spDeflbl.setTextFill(Color.web("#0000FF")); // Sets the color of spDeflbl to Blue
							break; // Break to prevent conflicts
						case "Bold": // If the nature is Bold
							defenselbl.setTextFill(Color.web("#FF0000")); // Sets the color of defenselbl to Red
							attacklbl.setTextFill(Color.web("#0000FF")); // Sets the color of attacklbl to Blue
							break; // Break to prevent conflicts
						case "Relaxed": // If the nature is Relaxed
							defenselbl.setTextFill(Color.web("#FF0000")); // Sets the color of defenselbl to Red
							speedlbl.setTextFill(Color.web("#0000FF")); // Sets the color of speedlbl to Blue
							break; // Break to prevent conflicts
						case "Impish": // If the nature is Impish
							defenselbl.setTextFill(Color.web("#FF0000")); // Sets the color of defenselbl to Red
							spAtklbl.setTextFill(Color.web("#0000FF")); // Sets the color of spAtk to Blue
							break; // Break to prevent conflicts
						case "Lax": // If the nature is Lax
							defenselbl.setTextFill(Color.web("#FF0000")); // Sets the color of defenselbl to Red
							spDeflbl.setTextFill(Color.web("#0000FF")); // Sets the color of spDeflbl to Blue
							break; // Break to prevent conflicts
						case "Timid": // If the nature is Timid
							speedlbl.setTextFill(Color.web("#FF0000")); // Sets the color of speedlbl to Red
							attacklbl.setTextFill(Color.web("#0000FF")); // Sets the color of attacklbl to Blue
							break; // Break to prevent conflicts
						case "Hasty": // If the nature is Hasty
							speedlbl.setTextFill(Color.web("#FF0000")); // Sets the color of speedlbl to Red
							defenselbl.setTextFill(Color.web("#0000FF")); // Sets the color of defenselbl to Blue
							break; // Break to prevent conflicts
						case "Jolly": // If the nature is Jolly
							speedlbl.setTextFill(Color.web("#FF0000")); // Sets the color of speedlbl to Red
							spAtklbl.setTextFill(Color.web("#0000FF")); // Sets the color of spAtk to Blue
							break; // Break to prevent conflicts
						case "Naive": // If the nature is Naive
							speedlbl.setTextFill(Color.web("#FF0000")); // Sets the color of speedlbl to Red
							spDeflbl.setTextFill(Color.web("#0000FF")); // Sets the color of spDeflbl to Blue
							break; // Break to prevent conflicts
						case "Modest": // If the nature is Modest
							spAtklbl.setTextFill(Color.web("#FF0000")); // Sets the color of spAtklbl to Red
							attacklbl.setTextFill(Color.web("#0000FF")); // Sets the color of attacklbl to Blue
							break; // Break to prevent conflicts
						case "Mild": // If the nature is Mild
							spAtklbl.setTextFill(Color.web("#FF0000")); // Sets the color of spAtklbl to Red
							defenselbl.setTextFill(Color.web("#0000FF")); // Sets the color of defenselbl to Blue
							break; // Break to prevent conflicts
						case "Quiet": // If the nature is Quiet
							spAtklbl.setTextFill(Color.web("#FF0000")); // Sets the color of spAtklbl to Red
							speedlbl.setTextFill(Color.web("#0000FF")); // Sets the color of speedlbl to Blue
							break; // Break to prevent conflicts
						case "Rash": // If the nature is Rash
							spAtklbl.setTextFill(Color.web("#FF0000")); // Sets the color of spAtklbl to Red
							spDeflbl.setTextFill(Color.web("#0000FF")); // Sets the color of spDeflbl to Blue
							break; // Break to prevent conflicts
						case "Calm": // If the nature is Calm
							spDeflbl.setTextFill(Color.web("#FF0000")); // Sets the color of spDeflbl to Red
							attacklbl.setTextFill(Color.web("#0000FF")); // Sets the color of attacklbl to Blue
							break; // Break to prevent conflicts
						case "Gentle": // If the nature is Gentle
							spDeflbl.setTextFill(Color.web("#FF0000")); // Sets the color of spDeflbl to Red
							defenselbl.setTextFill(Color.web("#0000FF")); // Sets the color of defenselbl to Blue
							break; // Break to prevent conflicts
						case "Sassy": // If the nature is Sassy
							spDeflbl.setTextFill(Color.web("#FF0000")); // Sets the color of spDeflbl to Red
							speedlbl.setTextFill(Color.web("#0000FF")); // Sets the color of speedlbl to Blue
							break; // Break to prevent conflicts
						case "Careful": // If the nature is Careful
							spDeflbl.setTextFill(Color.web("#FF0000")); // Sets the color of spDeflbl to Red
							spAtklbl.setTextFill(Color.web("#0000FF")); // Sets the color of spAtk to Blue
							break; // Break to prevent conflicts
						default: // If the nature is neutral
							hplbl.setTextFill(Color.web("#000000")); // Sets the color of hplbl to black, lol
						} // End of switch
					baseTotal = 0; // Resets baseTotal back to 0
					for (int i = 0; i < baseStats.length; i++) { // For loop to get the sum of the base stats
						baseTotal += baseStats[i]; // Adds the base stats of that slot to EVTotal
					} // End for loop
					totalBaseStatsTF.setText(Integer.toString(baseTotal)); // Sets the text of totalBaseStatsTF with baseTotal
					IVTotal = 0; // Resets IVTotal back to 0
					for (int i = 0; i < IVs.length; i++) { // For loop to get the sum of the IVs
						IVTotal += IVs[i]; // Adds the IVs of that slot to IVTotal
					} // End for loop
					totalIVsTF.setText(Integer.toString(IVTotal)); // Sets the TextField to the sum of the IVs
					totalStatsTotal = 0; // Resets totalStatsTotal to 0
					realStatsInt = new int[realStats.length]; // Sets the size of the array by using realStats' length
					for (int i = 0; i < realStats.length; i++) { // Loop to "fix" the realStats
						Math.floor(realStats[i]); // 1. Rounds down the value at the array index
						realStatsInt[i] = (int) realStats[i]; // 2. Casts the array to int
						totalStatsTotal += realStats[i]; // 3. Find the sum of the total stats
					} // End for loop
					hpStatTF.setText(Integer.toString(realStatsInt[0])); // Sets the text of hpStatTF with realStatsInt[0]
					atkStatTF.setText(Integer.toString(realStatsInt[1])); // Sets the text of atkStatTF with realStatsInt[1]
					defStatTF.setText(Integer.toString(realStatsInt[2])); // Sets the text of defStatTF with realStatsInt[2]
					spAtkStatTF.setText(Integer.toString(realStatsInt[3])); // Sets the text of spAtkStatTF with realStatsInt[3]
					spDefStatTF.setText(Integer.toString(realStatsInt[4])); // Sets the text of spDefStatTF with realStatsInt[4]
					speStatTF.setText(Integer.toString(realStatsInt[5])); // Sets the text of speStatTF with realStatsInt[5]
					totalStatsTF.setText(Integer.toString(totalStatsTotal)); // Sets the text of totalStatsTF with totalStatsTotal
					HPImageView.setImage(null); // Sets HPImageView to show nothing
					for (int i = 0; i < bits.length; i++) { // For loop to determine bits
						if (IVs[i] % 2 != 0) // If modulus = 1...
							bits[i] = 1; // Sets bit to 1
						else // Else...
							bits[i] = 0; // Sets bit to 0
					} // End loop
					/** Calculates part 1 for Hidden Power type */
					double pt1 = bits[0] + (2 * bits[1]) + (4 * bits[2]) + (8 * bits[5]) + (16 * bits[3]) + (32 * bits[4]);
					double pt2 = pt1 * 15; // multiplies part 1 by 15
					double pt3 = pt2 / 63; // Divides part 2 by 63
					int HPType = (int)Math.floor(pt3); // Rounds down part 3 and casts it to an interger
					switch(HPType) { // Switch statement to determine what type Hidden Power will be
					case 0: // If HPType = 0...
						HPImage = new Image("types/Fighting.gif"); // Sets the Fighting type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 1: // If HPType = 1...
						HPImage = new Image("types/Flying.gif"); // Sets the Flying type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 2: // If HPType = 2...
						HPImage = new Image("types/Poison.gif"); // Sets the Poison type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 3: // If HPType = 3...
						HPImage = new Image("types/Ground.gif"); // Sets the Ground type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 4: // If HPType = 4...
						HPImage = new Image("types/Rock.gif"); // Sets the Rock type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 5: // If HPType = 5...
						HPImage = new Image("types/Bug.gif"); // Sets the Bug type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 6: // If HPType = 6...
						HPImage = new Image("types/Ghost.gif"); // Sets the Ghost type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 7: // If HPType = 7...
						HPImage = new Image("types/Steel.gif"); // Sets the Steel type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 8: // If HPType = 8...
						HPImage = new Image("types/Fire.gif"); // Sets the Fire type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 9: // If HPType = 9...
						HPImage = new Image("types/Water.gif"); // Sets the Water type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 10: // If HPType = 10...
						HPImage = new Image("types/Grass.gif"); // Sets the Grass type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 11: // If HPType = 11...
						HPImage = new Image("types/Electric.gif"); // Sets the Electric type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 12: // If HPType = 12...
						HPImage = new Image("types/Psychic.gif"); // Sets the Psychic type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 13: // If HPType = 13...
						HPImage = new Image("types/Ice.gif"); // Sets the Ice type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 14: // If HPType = 14...
						HPImage = new Image("types/Dragon.gif"); // Sets the Dragon type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
					case 15: // If HPType = 15...
						HPImage = new Image("types/Dark.gif"); // Sets the Dark type pic
						HPImageView.setImage(HPImage); // Sets it into HPImageView
						break; // Separate cases
						default: // Else...
							HPImageView.setImage(null); // Nothing appears
					} // End switch
				} // end else
			} // End else
		} // End try block 
		catch (NumberFormatException e1) { // Catch block to make sure all textfields are filled with integers
			final Stage notNumber = new Stage(); // Creates a new stage called notNumber
			notNumber.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
			notNumber.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
		    VBox dialogVbox = new VBox(20); // Creates a VBox called dialogVbox
		    dialogVbox.getChildren().add(new Text("Make sure all variables are numbers/filled")); // Adds everything to dialogVbox
		    Scene dialogScene = new Scene(dialogVbox, 225, 20); // Creates a new scene called dialogScene
		    notNumber.setScene(dialogScene); // Sets the dialogScene scene to the notNumber stage
		    notNumber.getIcons().add(new Image("icon/pokeballIcon.png"));
		    notNumber.show(); // Shows the notNumber stage
		} // End catch block
	}
	
	/** Method that opens up the gen 7 competitive page */
	public void openGen7Link() {
		getHostServices().showDocument(gen7linkStr); // This will open the link to the gen 7 Smogon page for that Pokémon
	} // End of openGen7Link method
	
	/** Method that opens up the gen 8 competitive page */
	public void openGen8Link() {
		getHostServices().showDocument(gen8linkStr); // This will open the link to the gen 8 Smogon page for that Pokémon
	} // End of openGen8Link method
	
	/** Method that gets info about the Pokémon selected */
	public void getPokemon() {
		formBox.setValue(null); // If the Pokémon had forms in formBox, they're gone now
		otherFormsList.clear(); // Resets the otherFormsList ObservableList
		formBox.setVisibleRowCount(formBox.getVisibleRowCount()); // Removes empty space from formBox
		rockruffAbilityFI.setVisible(false); // Hides the label that reads Rockruff's 4th ability
		gen7lbl.setVisible(true); // Makes gen 7 label in Abilities appear
		gen8lbl.setVisible(true); // Makes gen 8 label in Abilities appear
		gen8lbl.setLayoutX(16); // Sets the x location of gen8lbl2 to 16
		gen8lbl.setLayoutY(608); // Sets the y location of gen8lbl2 to 608
		gen8link.setLayoutX(54); // Sets the x location of gen8link2 to 54
		gen8link.setLayoutY(605); // Sets the y location of gen8link2 to 605
		try { // try block for the createPokemon query statement
			/** Query statement that gets all the information of the Pokémon at the specific Pokémon name */
			createPokemon = statement.executeQuery("SELECT * FROM pokemon WHERE pokemon_name = \"" +pokemonNameBox.getValue().trim()+ "\";");
			natureCombobox.getSelectionModel().select(0); // Sets the nature in the stat calc tab to Hardy (neutral nature)
			attacklbl.setTextFill(Color.web("#000000")); // Sets the color of attacklbl to black
			defenselbl.setTextFill(Color.web("#000000")); // Sets the color of defenselbl to black
			spAtklbl.setTextFill(Color.web("#000000")); // Sets the color of spAtklbl to black
			spDeflbl.setTextFill(Color.web("#000000")); // Sets the color of spDeflbl to black
			speedlbl.setTextFill(Color.web("#000000")); // Sets the color of speedlbl to black
			cryButton.setVisible(true); // If the cry button isn't on yet, it turns it on now
			type2PicView.setImage(null); // If the newly selected Pokémon doesn't have a 2nd type
			ability2FI.setText(" "); // If the newly selected Pokémon doesn't have a 2nd ability
			hiddenAbilityFI.setText(" "); // If the newly selected Pokémon doesnt have a hidden ability
			eggGroup2.setText(" "); // If the newly selected Pokémon doesn't have a 2nd egg group
			HPIVsTF.setText("0");; // Sets the text of HPIVsTF to 0
			atkIVsTF.setText("0"); // Sets the text of atkIVsTF to 0
			defIVsTF.setText("0"); // Sets the text of defIVsTF to 0
			spDefIVsTF.setText("0"); // Sets the text of spDefIVsTF to 0
			spAtkIVsTF.setText("0"); // Sets the text of spAtkIVsTF to 0
			speIVsTF.setText("0"); // Sets the text of speIVsTf to 0
			HPEVsTF.setText("0"); // Sets the text of HPEVsTF to 0
			atkEVsTF.setText("0"); // Sets the text of atkEVsTF to 0
			defEVsTF.setText("0"); // Sets the text of defEVsTF to 0
			spAtkEVsTF.setText("0"); // Sets the text of spAtkEVsTF to 0
			spDefEVsTF.setText("0"); // Sets the text of spDefEVsTF to 0
			speEVsTF.setText("0"); // Sets the text of speEVsTF to 0
			totalBaseStatsTF.clear(); // Clears out totalBaseStatsTF
			totalIVsTF.clear(); // Clears out totalIVsTF
			totalEVsTF.clear(); // Clears out totalEVsTF
			hpStatTF.clear(); // Clears out hpStatTF
			atkStatTF.clear(); // Clears out atkStatTF
			defStatTF.clear(); // Clears out defStatTF
			spAtkStatTF.clear(); // Clears out spAtkStatTF
			spDefStatTF.clear(); // Clears out spDefStatTF
			speStatTF.clear(); // Clears out speStatTF
			totalStatsTF.clear(); // Clears out totalStatsTF
			ability1FI.setLayoutX(598); // Sets the x location of ability1FI to 598
			ability1FI.setLayoutY(8); // Sets the y location of ability1FI to 8
			ability2FI.setLayoutX(598); // Sets the x location of ability2FI to 598
			ability2FI.setLayoutY(25); // Sets the y location of ability2FI to 25
			hiddenAbilityFI.setLayoutX(598); // Sets the x location of hiddenAbilityFI to 598
			hiddenAbilityFI.setLayoutY(44); // Sets the y location of hiddenAbilityFI to 44
			getStatsButton.setVisible(true); // If it's the first Pokémon, it turns the Get Stats button on
			shinyCheck.setSelected(false); // If the shiny checkbox was on, it is now off
			femaleCheck.setSelected(false); // If the female checkbox was on, it is now off
			femaleCheck.setVisible(false); // Makes femaleCheck disappear
			hiddenAbilityFI.setTextFill(Color.web("#000000")); // Set the color of hiddenAbilityFI to black
			ability2FI.setFont(Font.font("System Bold", FontWeight.NORMAL, 12)); // Sets the font of ability2 to regular
			ability2FI.setTextFill(Color.web("#000000")); // Sets the color of ability2FI to black
			menuitem1.setDisable(false); // Enables menuitem1
			menuitem4.setDisable(false); // Enables menuitem4
			createPokemon.next(); // Begins grabbing data for that specific Pokémon
			HPImageView.setImage(null); // Sets HPImageView to show nothing
			shinyCheck.setVisible(true); // Makes shinyCheck appear
			pokemonID = createPokemon.getInt(1); // Sets pokemonID to the value of that specific row in column 1 (pokemon_id)
			pokemonNoBox.setText(Integer.toString(pokemonID)); // Sets the TextField to the ID of the pokémon
			/** Sets the pokemonNameID to the value of the specific row in column 2 (pokemon_name_id) */
			pokemonNameID = createPokemon.getString(2);
			pokemonName = createPokemon.getString(3); // Sets the pokemonName to the value of the specific row in column 3 (pokemon_name)
			pkmnNameFillIn.setText(pokemonName); // Sets the label to the name of the Pokémon
			pokemonFormNameHolder = pokemonName; // Sets pokemonFormNamHolder with whatever is in pokemonName
			pokemonNameBox.setPromptText(pokemonName); // Sets the text of the pokemonNameBox to the name of the Pokémon
			pokemonsprite = new Image("sprites/" +pokemonID+ ".png"); // Sets pokemonsprite to the sprite of the Pokémon selected
			pokemonSpriteView.setImage(pokemonsprite); // Sets the image to the pokemon name ID, and then shows the image
			cryLoc = "cries/" +pokemonNameID+ ".mp3"; // Sets cryLoc to the location of the Pokémon's cry
			pokemonCry = new Media(getClass().getResource(cryLoc).toExternalForm()); // Sets pokemonCry to the source of the Pokémon's cry
			cryPlayer = new AudioClip(pokemonCry.getSource()); // Sets cryPlayer with the Pokémon's cry to play
			baseStats[0] = createPokemon.getInt(4); // Sets baseStats[0] to the value of the specific row at column 4 (base_hp)
			baseHPTF.setText(Integer.toString(baseStats[0])); // Sets the TextField to baseStats[0]
			baseStats[1] = createPokemon.getInt(5); // Sets baseStats[1] to the value of the specific row at column 5 (base_attack)
			baseAtkTF.setText(Integer.toString(baseStats[1])); // Sets the TextField to baseStats[1]
			baseStats[2] = createPokemon.getInt(6); // Sets baseStats[2] to the value of the specific row at column 6 (base_defense)
			baseDefTF.setText(Integer.toString(baseStats[2])); // Sets the TextField to baseStats[2]
			baseStats[3] = createPokemon.getInt(7); // Sets baseStats[3] to the value of the specific row at column 7 (base_special_attack)
			baseSpAtkTF.setText(Integer.toString(baseStats[3])); // Sets the TextField to baseStats[3]
			baseStats[4] = createPokemon.getInt(8); // Sets baseStats[4] to the value of the specific row at column 8 (base_special_defense)
			baseSpDefTF.setText(Integer.toString(baseStats[4])); // Sets the TextField to baseStats[4]
			baseStats[5] = createPokemon.getInt(9); // Sets baseStats[5] to the value of the specific row at column 9 (base_speed)
			baseSpeTF.setText(Integer.toString(baseStats[5])); // Sets the TextField to baseStats[5]
			type1pic = new Image("types/" +createPokemon.getString(10)+ ".gif"); // Sets type1pic to the Pokémon's primary type
			type1PicView.setImage(type1pic); // Sets type1PicView with type1pic
			if (createPokemon.getString(11) != null) { // If the Pokémon does have a 2nd type...
				type2pic = new Image("types/" +createPokemon.getString(11)+ ".gif"); // Sets type2pic to the Pokémon's secondary type
				type2PicView.setImage(type2pic); // Sets type2PicView with type2pic
			} // End If
			catchRate = createPokemon.getInt(12); // Sets catchRate to the value of the specific row at column 12 (catch_rate)
			catchRateFI.setText(Integer.toString(catchRate)); // Sets the label to the capture rate
			baseEXPYield = createPokemon.getInt(13); // Sets baseEXPYield to the value of the specific row at column 13 (base_exp_yield)
			BaseEXPFI.setText(Integer.toString(baseEXPYield)); // Sets the label the base EXP Yield
			EVYield = createPokemon.getString(14); // Sets EVYield to the value of the specific row at column 14 (ev_yield)
			evsEarnedFI.setText(EVYield); // Sets the label to the EV Yield
			genderRatio = createPokemon.getString(15); // Sets genderRatio to the value of the specific row at column 15 (gender_ratio) 
			genderRatioFillIn.setText(genderRatio); // Sets the label to the gender ratio
			eggCycle = createPokemon.getInt(16); // Sets eggCycle to the value of the specific row at column 16 (egg_cycle)
			eggCycleFI.setText(Integer.toString(eggCycle)); // Sets the label to the base egg steps
			baseFriendship = createPokemon.getInt(17); // Sets baseFriendship to the value of the specific row at column 17 (base_friendship)
			baseHapFI.setText(Integer.toString(baseFriendship)); // Sets the label to the base happiness
			levelUpType = createPokemon.getInt(18); // Sets levelUpType to the value of the specific row at column 18 (level_up_type)
			expGrowthFI.setText(Integer.toString(levelUpType)+ " points"); // Sets the label to the experience growth
			eggGroups[0] = createPokemon.getString(19); // Sets eggGroups[0] to the value of the specific row at column 19 (egg_group_1)
			eggGroup1.setText(eggGroups[0]); // Sets the label to egg group 1
			eggGroups[1] = createPokemon.getString(20); // Sets eggGroups[1] to the value of the specific row at column 20 (egg_group_2)
			if (eggGroups[1] != null) { // If the Poké has a 2nd egg group...
				eggGroup2.setText(eggGroups[1]); // ...sets the label to egg group 2, only if it has one
			} // End if
			abilities[0] = createPokemon.getString(21); // Sets abilities[0] to the value of the specific row at column 21 (ability_1)
			ability1FI.setText(abilities[0]); // Sets the label to ability 1
			abilities[1] = createPokemon.getString(22); // Sets abilities[1] to the value of the specific row at column 22 (ability_2)
			if (abilities[1] != null) { // If the Pokémon has a second ability...
				ability2FI.setText(abilities[1]); // ...sets the label to ability 2
			} // End if
			hiddenAbility = createPokemon.getString(23); // Sets hiddenAbility to the value of the specific row at column 23 (hidden_ability)
			if (hiddenAbility != null) { // If the Pokémon has a hidden ability...
				hiddenAbilityFI.setText(hiddenAbility); // ... sets the label to the hidden ability
				if (abilities[1] == null) { // If the Pokémon does NOT have a second ability... 
					hiddenAbilityFI.setLayoutX(598); // Sets the x location of hiddenAbilityFI to 598
					hiddenAbilityFI.setLayoutY(25); // Sets the y location of hiddenAbilityFI to 25
				} // End if
			} // End If
			if (pokemonName.equals("Rockruff")) { // If the Pokémon selected is Rockruff...
				rockruffAbilityFI.setVisible(true); // Shows Rockruff's 4th event-exclusive ability
			} // End if
			if (pokemonName.equals("Greninja")) { // If the Pokémon selected is Greninja
				ability2FI.setFont(fontBold); // Change the font of ability2FI to bold
				hiddenAbilityFI.setTextFill(Color.web("#FF0000")); // Changes the color of hiddenAbilityFI to red
			} // End if
			if (pokemonName.equals("Zygarde")) { // If the Pokémon selected is Zygarde
				ability2FI.setFont(fontBold); // Sets the font of ability2FI to bold
				ability2FI.setTextFill(Color.web("#FF0000")); // Sets the color of ability2FI to red
			} // End if
			pokemonColor = createPokemon.getString(24); // Sets pokemonColor to the value of the specific row at column 24 (pokemon_color)
			pokemonColorFI.setText(pokemonColor); // Sets the label to the color of the Pokémon
			if (pokemonColor.equals("Red")) { // If the Pokémon color is Red ... 
				pokemonColorFI.setTextFill(Color.web("#FF0000")); // Sets the color of it to Red
			} // End if
			else if (pokemonColor.equals("Blue")) { // else if the Pokémon color is Blue...
				pokemonColorFI.setTextFill(Color.web("#0000FF")); // Sets the color of pokemonColorFI to Blue
			} // End else if
			else if (pokemonColor.equals("Yellow")) { // else if the Pokémon color is Yellow...
				pokemonColorFI.setTextFill(Color.web("#FFFF00")); // Sets the color of pokemonColorFI to Yellow
			} // End else if
			else if (pokemonColor.equals("Green")) { // else if the Pokémon color is Green...
				pokemonColorFI.setTextFill(Color.web("#008000")); // Sets the color of pokemonColorFI to Green
			} // End else if
			else if (pokemonColor.equals("Brown")) { // else if the Pokémon color is Brown...
				pokemonColorFI.setTextFill(Color.web("#A25A2A")); // Sets the color of pokemonColorFI to Brown
			} // End else if
			else if (pokemonColor.equals("Purple")) { // else if the Pokémon color is Purple...
				pokemonColorFI.setTextFill(Color.web("#800080")); // Sets the color of pokemonColorFI to Purple
			} // End else if
			else if (pokemonColor.equals("Gray")) { // else if the Pokémon color is Gray...
				pokemonColorFI.setTextFill(Color.web("#808080")); // Sets the color of pokemonColorFI to Gray
			} // End else if
			else if (pokemonColor.equals("White")) { // else if the Pokémon color is White...
				pokemonColorFI.setTextFill(Color.web("#FFFFFF")); // Sets the color of pokemonColorFI to White
			} // End else if
			else if (pokemonColor.equals("Pink")) { // else if the Pokémon color is Pink...
				pokemonColorFI.setTextFill(Color.web("#FFC0CB")); // Sets the color of pokemonColorFI to Pink
			} // End else if
			else { // Else...
				pokemonColorFI.setTextFill(Color.web("#000000")); // Sets the color of pokemonColorFI to Black
			} // End else
			pokemonHeight = createPokemon.getDouble(25); // Sets pokemonHeight to the value of the specifc row at column 25 (pokemon_height)
			heightFillIn.setText(pokemonHeight+ " m"); // Sets the label to the Pokémon height in meters
			inches = pokemonHeight * 39.37; // This converts meters to inches
			feet = inches / 12; // This converts inches to feet
			Math.floor(feet); // Makes sure that it rounds down
			feetInt = (int) feet; // casts the feet to an int
			trueinches = inches % 12; // This gets inches
			Math.floor(trueinches); // Makes sure that it rounds down
			trueinchesInt = (int) trueinches; // casts inches to an int
			/** Sets the label to the height of the Pokémon in feet and inches */
			heightInchesFI.setText(Integer.toString(feetInt)+ "'" +Integer.toString(trueinchesInt)+"\"");
			pokemonWeight = createPokemon.getDouble(26); // Sets pokemonWeight to the value of the specific row at column 26 (pokemon_weight)
			weightFillIn.setText(pokemonWeight+ " kg"); // Sets the label to the weight of the Pokémon in kilograms
			pounds = pokemonWeight * 2.205; // Converts kilograms to pound
			/** Sets the label to the weight of the Pokémon in pounds */
			weightpoundsFI.setText(Double.toString(Math.round(pounds * 10) / 10.0)+ " lbs");
			pokemonClass = createPokemon.getString(27); // Sets pokemonClass to the value of the specific row at column 27 (pokemon_type)
			classFillIn.setText(pokemonClass+ " Pokémon"); // Sets the label to the Pokémon's classification
			/** Sets pokemonBiology to the value of the specific row at column 28 (pokemon_biology) */
			pokemonBiology = createPokemon.getString(28);
			/** Sets pokemonTrivia to the value of the specific row at column 29 (pokemon_trivia) */
			pokemonTrivia = createPokemon.getString(29);
			pokemonOrigin = createPokemon.getString(30); // Sets pokemonOrigin to the value of the specific row at column 30 (pokemon_origin)
			/** Sets pokemonNameOrigin to the value of the specific row at column 31 (pokemon_name_origin) */
			pokemonNameOrigin = createPokemon.getString(31);
			pokedexTA.setText("Biology:\n" +pokemonBiology+ "\n\nTrivia:\n" +pokemonTrivia+ "\n\nOrigin:\n" +pokemonOrigin+ "\n\nName origin:"
					+ "\n" +pokemonNameOrigin); // Sets TextArea to the Pokémon's biology, trivia, name origin, and origin
			gen7linkStr = createPokemon.getString(37); // Sets gen7linkStr to the value of the specific row at column 37 (smogon_link_gen_7)
			gen7link.setText(gen7linkStr); // Sets gen7link to the string in gen7linkStr
			gen8linkStr = createPokemon.getString(38); // Sets gen8linkStr to the value of the specific row at column 38 (smogon_link_gen_8)
			gen8link.setText(gen8linkStr); // Sets gen8link to the string in gen8linkStr
			if (gen7linkStr == null) { // If gen7linkStr has nothing in it...
				gen7lbl.setVisible(false); // Makes gen7lbl invisible
				gen8lbl.setLayoutX(16); // Sets the x location of gen8lbl to 16
				gen8lbl.setLayoutY(585); // Sets the y location of gen8lbl to 560
				gen8link.setLayoutX(54); // Sets the x location of gen8link to 54
				gen8link.setLayoutY(583); // Sets the y location of gen8link to 557
			} // End if
			if (gen8linkStr == null) { // If gen8linkstr has nothing in it...
				gen8lbl.setVisible(false); // Makes gen8lbl invisible
			} // End if
			/** Sets hasFemaleForm to the value of the specific row at column 32 (has_female_form) */
			hasFemaleForm = createPokemon.getBoolean(32);
			if (hasFemaleForm == true) { // If the Pokémon does NOT have a female form...
				femaleCheck.setVisible(true); // The femaleCheck checkbox appears
			} // End if
			/** Sets pokemonEvolution to the value of the specific row at column 33 (evolution) */
			pokemonEvolution = createPokemon.getString(33);
			EvoTA.setText(pokemonEvolution); // Sets the TextArea to the evolution text for that Pokémon
			/** Sets hasOtherForms to the value of the specific row at column 34 (has_other_form) */
			hasOtherForms = createPokemon.getBoolean(34); 
			if (hasOtherForms == false) { // If the Pokémon does NOT have other forms...
				formBox.setVisible(false); // The formBox ComboBox disappears
			} // End if
			else { // Else...
				formBox.setVisible(true); // The formBox ComboBox appears
				otherFormIndex = createPokemon.getInt(35); // Sets otherFormIndex to the value of the specific row at column 35 (formList)
				/** Sets the contents of the list with the forms */
				otherFormsList = FXCollections.observableArrayList(Arrays.asList(otherForms[otherFormIndex]));
				formBox.setItems(otherFormsList); // Sets the contents of the Form ComboBox with otherFormList
				/** Sets otherFormSelection to the value at the specific row at column 36 (other_form_name) */
				otherFormSelection = createPokemon.getInt(36);
				/** Sets the prompt text of the form ComboBox with what's selected at the otherForms 2D array */
				formBox.getSelectionModel().select(otherFormSelection);
			} // End else
		} // End try
		catch (SQLException e1) { // Catch block, empty because there is no way of an exception happening
			final Stage doesNotExist = new Stage(); // Create a new stage called doesNotExist
			doesNotExist.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
			doesNotExist.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
            VBox dneVBox = new VBox(); // Create a VBox called dneVBox
            AnchorPane dneAnchor = new AnchorPane(); // Creates an AnchorPane called ohnoAnchor
            Button okButton = new Button("OK"); // Creates a new button called okButton
            okButton.setLayoutX(81); // Sets the x location of okButton to 81
            okButton.setLayoutY(56); // Sets the y location of okButton to 56
            Label oklbl = new Label("Pokémon doe not exist :("); // Label that says Incorrect username/password
            oklbl.setLayoutX(32); // Sets the x location of oklbl to 32
            oklbl.setLayoutY(14); // Sets the y location of oklbl to 14
            dneAnchor.getChildren().addAll(okButton,oklbl); // Adds everything to the ohnoAnchor
            dneVBox.getChildren().add(dneAnchor); // Adds everything to the ohnovbox
            Scene dialogScene = new Scene(dneVBox, 225, 100); // Creates a new scene called dialogScene
            doesNotExist.setScene(dialogScene); // Sets the dialogScene scene to ohno stage
            doesNotExist.getIcons().add(new Image("icon/pokeballIcon.png"));
            doesNotExist.show(); // Shows ohno stage
            doesNotExist.setResizable(false); // Makes sure that ohno can't be resized
            okButton.setOnAction(e2 -> { // Event that closes ohno
            	doesNotExist.close(); // Closes ohno
            }); // End okButton event
		} // End Catch
	} // End of method
	
	/** EventHandler method for the shiny and female checkboxes */
	EventHandler<ActionEvent> checkBoxes = e -> {
		if (shinyCheck.isSelected() && femaleCheck.isSelected()) { // If shinyCheck AND femaleCheck are on...
			/** Loads up the shiny female picture of that Pokémon and sets it to shinyFemalePoke */
			shinyFemalePoke = new Image("sprites/" +pokemonID+ "femaleshiny.png");
			pokemonSpriteView.setImage(shinyFemalePoke); // Puts shinyFemalePoke into pokemonSpriteView
		} // end if
		else if (shinyCheck.isSelected()) { // else if shinyCheck is on...
			/** Loads up the shiny picture of that Pokémon and sets it to shinyPoke */
			Image shinyPoke = new Image("sprites/" +pokemonID+ "shiny.png");
			pokemonSpriteView.setImage(shinyPoke); // Puts shinyPoke into pokemonSpriteView
		} // end else if
		else if (femaleCheck.isSelected()) { // else if femaleCheck is on...
			/** Loads up the female picture of that Pokémon and sets it to femalePoke */
			Image femalePoke = new Image("sprites/" +pokemonID+ "female.png");
			pokemonSpriteView.setImage(femalePoke); // Puts femalePoke into pokemonSpriteView
		} // End else if
		else { // Else...
			pokemonsprite = new Image("sprites/" +pokemonID+ ".png"); // Sets pokemonsprite to the sprite of the Pokémon selected
			pokemonSpriteView.setImage(pokemonsprite); // Puts pokemonsprite into pokemonSpriteView
		} // End else
	}; // End checkBoxes EventHandler
	
	/** Method that gets info about the ability selected */
	public void getAbility() {
		try { // try block for the getAbility query statement
			/** Query statement that calls for the ability at the specific location */
			getAbility = statement.executeQuery("SELECT * FROM abilities\r\n WHERE ability_name = '" +abilityComboBox.getValue()+ "';");
			getAbility.next(); // Begins grabbing data for that specific ability
			abilityName = getAbility.getString(2); // Sets abilityName to the value at the specific row at column 2 (ability_name)
			abilityNameFI.setText(abilityName); // Sets the label to the ability's name
			/** Sets abilityshortDesc to the value at the specific row at column 3 (abiliy_short_description) */
			abilityshortDesc = getAbility.getString(3);
			gameTextFI.setText(abilityshortDesc); // Sets the TextArea to the ability's shorter description
			/** Sets abilityInDepthDesc to the value at the specific row at column 4 (ability_long_description) */
			abilityInDepthDesc = getAbility.getString(4);
			inDepthEffectFI.setText(abilityInDepthDesc); // Sets the TextArea to the ability's more in-depth description
		} // End of try block
		catch (SQLException e1) { // Catch block, empty because there is no way of an exception happening
		} // End of catch block
	} // End of method
	
	/** Method that gets info about the item selected */
	public void getItem() {
		itemImageView.setImage(null); // Makes the itemImageView blank
		/** String to be used for the following ResultSet */
		itemQuery = "SELECT * FROM items WHERE item_name = \"" +itemNameBox.getValue()+ "\";";
		try { // try block for the getItem query statment
			getItem = statement.executeQuery(itemQuery); // Query statement that calls for the item at the specific location
			getItem.next(); // Begins grabbing data for that specific item
			itemImage = new Image("items/" +getItem.getString(2)+ ".png"); // Sets itemimage to the sprite of the item selected
			itemImageView.setImage(itemImage); // Sets itemImage into itemImageView for the user to view
			itemNameFI.setText(getItem.getString(3)); // Sets itemNameFI to the value at the specific row at column 3 (item_name)
			itemPocketFI.setText(getItem.getString(4)); // Sets itemPocketFI to the value at the specific row at column 4 (item_pocket)
			itemDescTA.setText(getItem.getString(5)); // Sets itemDescTA to the value at the specific row at column 5 (item_description)
			/** Sets itemDescLongerTA to the value at the specific row at column 6 (item_long_desc) */
			itemDescLongerTA.setText(getItem.getString(6));
		} // End of try block
		catch (SQLException e1) { // Catch block, empty because there is no way of an exception happening
		} // End of catch block
	} // End of method
	
	/** Method that gets info about the move selected */
	public void getMove() {
		categorypicView.setImage(null); // Makes categorypicView have nothing in it
		try { // try block for the getMove query statement
			/** Query statement that calls for the move at the specific location */
			getMove = statement.executeQuery("SELECT * FROM moves WHERE move_name = \"" +movesBox.getValue()+ "\";");
			getMove.first(); // Begins grabbing data for that specific move
			moveName = getMove.getString(2); // Sets moveName to the value at the specific row at column 2 (move_name)
			moveNameFI.setText(moveName); // Sets the label to the move name
			moveTypePic = new Image("types/" +getMove.getString(3)+ ".gif"); // Sets moveTypePic to the move's type
			moveTypePicView.setImage(moveTypePic); // Puts moveTypePic into moveTypePicView
			moveCategory = getMove.getString(4); // Sets moveCategory to the value at the specific row at column 4 (move_category)
			typeLoc = "categories/" +moveCategory+ ".png";
			if (moveCategory.equals("Varies")) { // If the move category is Varies...
				categoryBigPic = new Image(typeLoc); // Sets categoryBigPic to the bigger move category sprite
				categorypicView.setImage(categoryBigPic); // Puts categoryBigPic into categorypicView
			} // End if
			else { // Else
				categorySmallPic = new Image(typeLoc); // Sets categorySmallPic to either sprite needed to load
				categoryPicSmallView.setImage(categorySmallPic); // Puts categorySmallPic into categoryPicSmallView
			} // End else
			movePP = getMove.getInt(5); // sets movePP to the value at the specific row at column 5 (move_pp)
			if (movePP == 0) { // If the move does NOT have any PP
				movePPFI.setText("--"); // Sets the text of movePPFI to --
			} // End if
			else { // Else...
			movePPFI.setText(Integer.toString(movePP)); // Sets label to the move PP
			} // End else
			moveBasePower = getMove.getInt(6); // Sets moveBasePower to the value at the specific row at column 6 (move_base_power)
			if (moveBasePower == 0) { // If the move does NOT have any base power
				moveBasePowerFI.setText("--"); // Sets the text of moveBasePowerFI to --
			} // End if
			else { // Else...
			moveBasePowerFI.setText(Integer.toString(moveBasePower)); // Sets label to the move base power
			} // End else
			moveAccuracy = getMove.getInt(7); // Sets moveAccuracy to the value at the specific row at column 7 (move_accuracy)
			if (moveAccuracy == 0) { // If the move does NOT have any accuracy...
				moveAccuracyFI.setText("--"); // Sets the text of moveAccuracyFI to --
			} // End if
			else { // Else...
			moveAccuracyFI.setText(Integer.toString(moveAccuracy)); // Sets the label to the move accuracy
			} // End else
			speedPriority = getMove.getInt(10); // Sets speed Priority to the value at the specific row at column 10 (move_priority)
			speedPriorityFI.setText(Integer.toString(speedPriority)); // Sets the label to the speed priority of the move
			moveShortDesc = getMove.getString(8); // Sets moveShortDesc to the value at the specific row at column 8 (move_short_description)
			shortDescMoveFI.setText(moveShortDesc); // Sets the TextArea to the move's shorter description
			/** Sets moveInDepthDesc to the value at the specific row at column 9 (move_long_description) */
			moveInDepthDesc = getMove.getString(9);
			inDepthDescMove.setText(moveInDepthDesc); // Sets the TextArea to the move's longer description
		} // End of try block
		catch (SQLException e1) { // Catch block, empty because there is no way of an exception happening
		} // End of catch block
	} // End of method
	
	/** Method that gets info about the Pokémon using their dex number */
	public void getPokemonNo() {
		try { // try block for the createPokemonNo query statement
			/** If the user inputted a number bigger than 890 or lower than 1... */
			if (Integer.parseInt(pokemonNoBox.getText()) > 890 || Integer.parseInt(pokemonNoBox.getText()) < 1) {
				final Stage dialog = new Stage(); // Create a new stage called dialog
                dialog.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
                dialog.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
                VBox dialogVbox = new VBox(20); // Create a new VBox called dialogVbox
                /** Gets everything together into the vbox */
                dialogVbox.getChildren().add(new Text("Value cannot be lower than 1 or higher than 890"));
                Scene dialogScene = new Scene(dialogVbox, 260, 20); // Creates a new scene called dialogScene to hold dialogVbox
                dialog.setScene(dialogScene); // Sets the dialogScene into the dialog stage
                dialog.getIcons().add(new Image("icon/pokeballIcon.png"));
                dialog.show(); // Makes the stage to appear
			} // End
			else { // Else
				formBox.setValue(null); // If the Pokémon had forms in their, they're gone now
				otherFormsList.clear(); // Resets the otherFormsList ObservableList
				formBox.setVisibleRowCount(formBox.getVisibleRowCount()); // Removes empty space from formBox
				gen7lbl.setVisible(true); // Makes the gen7lbl appear
				gen8lbl.setLayoutX(16); // Sets the x location of gen8lbl2 to 16
				gen8lbl.setLayoutY(608); // Sets the y location of gen8lbl2 to 608
				gen8link.setLayoutX(54); // Sets the x location of gen8link2 to 54
				gen8link.setLayoutY(605); // Sets the y location of gen8link2 to 605
				/** String to be used for the following ResultSet*/
				query = "SELECT * FROM pokemon WHERE pokemon_id = \"" +pokemonNoBox.getText()+ "\";";
				createPokemonNo = statement.executeQuery(query);
				cryButton.setVisible(true); // If the cry button isn't on yet, it turns it on now
				type2PicView.setImage(null); // If the newly selected Pokémon doesn't have a 2nd type
				ability2FI.setText(" "); // If the newly selected Pokémon doesn't have a 2nd ability
				hiddenAbilityFI.setText(" "); // If the newly selected Pokémon doesnt have a hidden ability
				eggGroup2.setText(" "); // If the newly selected Pokémon doesn't have a 2nd egg group
				HPIVsTF.setText("0");; // Sets the text at HPIVsTF to 0
				atkIVsTF.setText("0"); // Sets the text at atkIVsTF to 0
				defIVsTF.setText("0"); // Sets the text at defIVsTF to 0
				spDefIVsTF.setText("0"); // Sets the text at spDefIVsTF to 0
				spAtkIVsTF.setText("0"); // Sets the text at spAtkIVsTF to 0
				speIVsTF.setText("0"); // Sets the text at speIVsTF to 0
				HPEVsTF.setText("0"); // Sets the text at HPEVsTF to 0
				atkEVsTF.setText("0"); // Sets the text at atkEVsTF to 0
				defEVsTF.setText("0"); // Sets the text at defEVsTF to 0
				spAtkEVsTF.setText("0"); // Sets the text at spAtkEVsTF to 0
				spDefEVsTF.setText("0"); // Sets the text at spDefEVsTF to 0
				speEVsTF.setText("0"); // Sets the text at speEVsTF to 0
				totalBaseStatsTF.clear(); // Clears out totalBaseStatsTF
				totalIVsTF.clear(); // Clears out totalIVsTF
				totalEVsTF.clear(); // Clears out totalEVsTF
				hpStatTF.clear(); // Clears out hpStatTF
				atkStatTF.clear(); // Clears out atkStatTF
				defStatTF.clear(); // Clears out defStatTF
				spAtkStatTF.clear(); // Clears out spAtkStatTF
				spDefStatTF.clear(); // Clears out spDefStatTF
				speStatTF.clear(); // Clears out speStatTF
				totalStatsTF.clear(); // Clears out totalStatsTF
				getStatsButton.setVisible(true); // If it's the first Pokémon, it turns the Get Stats button on
				shinyCheck.setSelected(false); // If the shiny checkbox was on, it is now off
				femaleCheck.setSelected(false); // If the female checkbox was on, it is now off
				femaleCheck.setVisible(false); // Makes femaleCheck disappear
				ability1FI.setLayoutX(598); // Sets the x location of ability1FI to 598
				ability1FI.setLayoutY(8); // Sets the y location of ability1FI to 8
				ability2FI.setLayoutX(598); // Sets the x location of ability2FI to 598
				ability2FI.setLayoutY(25); // Sets the y location of ability2FI to 25
				hiddenAbilityFI.setLayoutX(598); // Sets the x location of hiddenAbilityFI to 598
				hiddenAbilityFI.setLayoutY(44); // Sets the y location of hiddenAbilityFI to 44
				rockruffAbilityFI.setVisible(false); // Sets the Rockruff ability to invisible
				hiddenAbilityFI.setTextFill(Color.web("#000000")); // Set the color of hiddenAbilityFI to black
				ability2FI.setFont(Font.font("System Bold", FontWeight.NORMAL, 12)); // Sets the font of ability2 to regular
				ability2FI.setTextFill(Color.web("#000000")); // Sets the color of ability2FI to black
				menuitem1.setDisable(false); // Enables menuitem1
				menuitem4.setDisable(false); // Enables menuitem4
				createPokemonNo.next(); // Begins grabbing data for that specific Pokemon's number
				natureCombobox.getSelectionModel().select(0); // Sets the nature in the stat calc to Hardy
				attacklbl.setTextFill(Color.web("#000000")); // Sets the color of attacklbl to black
				defenselbl.setTextFill(Color.web("#000000")); // Sets the color of defenselbl to black
				spAtklbl.setTextFill(Color.web("#000000")); // Sets the color of spAtklbl to black
				spDeflbl.setTextFill(Color.web("#000000")); // Sets the color of spDeflbl to black
				speedlbl.setTextFill(Color.web("#000000")); // Sets the color of speedlbl to black
				HPImageView.setImage(null); // Sets HPImageView to show nothing
				shinyCheck.setVisible(true); // Makes shinyCheck appear
				pokemonID = createPokemonNo.getInt(1); // Sets pokemonID to the value of that specific row in column 1 (pokemon_id)
				pokemonNoBox.setText(Integer.toString(pokemonID)); // Sets the TextField to the ID of the pokémon
				/** Sets the pokemonNameID to the value of the specific row in column 2 (pokemon_name_id) */
				pokemonNameID = createPokemonNo.getString(2);
				/** Sets the pokemonName to the value of the specific row in column 3 (pokemon_name) */
				pokemonName = createPokemonNo.getString(3);
				pkmnNameFillIn.setText(pokemonName); // Sets the label to the name of the Pokémon
				pokemonFormNameHolder = pokemonName; // Sets pokemonFormNamHolder with whatever is in pokemonName
				pokemonNameBox.getSelectionModel().select(pokemonID - 1); // Sets the pokemonNameBox to the name of the Pokémon
				pokemonsprite = new Image("sprites/" +pokemonID+ ".png"); // Sets pokemonsprite to the sprite of the Pokémon selected
				pokemonSpriteView.setImage(pokemonsprite); // Puts pokemonsprite into pokemonSpriteVoew
				cryLoc = "cries/" +pokemonNameID+ ".mp3"; // Gets the location of the mp3 into cryLoc
				/** Sets pokemonCry from the location of the mp3 file using cryLoc */
				pokemonCry = new Media(getClass().getResource(cryLoc).toExternalForm()); // Sets pokemonCry to the source of the Pokémon's cry
				cryPlayer = new AudioClip(pokemonCry.getSource()); // Puts pokemonCry into cryPlayer for the user to play
				baseStats[0] = createPokemonNo.getInt(4); // Sets baseStats[0] to the value of the specific row at column 4 (base_hp)
				baseHPTF.setText(Integer.toString(baseStats[0])); // Sets the TextField to baseStats[0]
				baseStats[1] = createPokemonNo.getInt(5); // Sets baseStats[1] to the value of the specific row at column 5 (base_attack)
				baseAtkTF.setText(Integer.toString(baseStats[1])); // Sets the TextField to baseStats[1]
				baseStats[2] = createPokemonNo.getInt(6); // Sets baseStats[2] to the value of the specific row at column 6 (base_defense)
				baseDefTF.setText(Integer.toString(baseStats[2])); // Sets the TextField to baseStats[2]
				/** Sets baseStats[3] to the value of the specific row at column 7 (base_special_attack) */
				baseStats[3] = createPokemonNo.getInt(7);
				baseSpAtkTF.setText(Integer.toString(baseStats[3])); // Sets the TextField to baseStats[3]
				/** Sets baseStats[4] to the value of the specific row at column 8 (base_special_defense) */
				baseStats[4] = createPokemonNo.getInt(8);
				baseSpDefTF.setText(Integer.toString(baseStats[4])); // Sets the TextField to baseStats[4]
				baseStats[5] = createPokemonNo.getInt(9); // Sets baseStats[5] to the value of the specific row at column 9 (base_speed)
				baseSpeTF.setText(Integer.toString(baseStats[5])); // Sets the TextField to baseStats[5]
				/** Sets type1pic to the location of the Pokémon's primary pic */
				type1pic = new Image("types/" +createPokemonNo.getString(10)+ ".gif");
				type1PicView.setImage(type1pic); // Puts type1pic into type1PicView to view
				if (createPokemonNo.getString(11) != null) { // If the pokemon has a secondary type...
					/** Sets type2pic to the location of the Pokémon's secondary pic */
					type2pic = new Image("types/" +createPokemonNo.getString(11)+ ".gif");
					type2PicView.setImage(type2pic); // Puts type2pic into type2PicView to view
				} // End if
				catchRate = createPokemonNo.getInt(12); // Sets catchRate to the value of the specific row at column 12 (catch_rate)
				catchRateFI.setText(Integer.toString(catchRate)); // Sets the label to the capture rate
				/** Sets baseEXPYield to the value of the specific row at column 13 (base_exp_yield) */
				baseEXPYield = createPokemonNo.getInt(13);
				BaseEXPFI.setText(Integer.toString(baseEXPYield)); // Sets the label the base EXP Yield
				EVYield = createPokemonNo.getString(14); // Sets EVYield to the value of the specific row at column 14 (ev_yield)
				evsEarnedFI.setText(EVYield); // Sets the label to the EV Yield
				genderRatio = createPokemonNo.getString(15); // Sets genderRatio to the value of the specific row at column 15 (gender_ratio) 
				genderRatioFillIn.setText(genderRatio); // Sets the label to the gender ratio
				eggCycle = createPokemonNo.getInt(16); // Sets eggCycle to the value of the specific row at column 16 (egg_cycle)
				eggCycleFI.setText(Integer.toString(eggCycle)); // Sets the label to the base egg steps
				/** Sets baseFriendship to the value of the specific row at column 17 (base_friendship) */
				baseFriendship = createPokemonNo.getInt(17);
				baseHapFI.setText(Integer.toString(baseFriendship)); // Sets the label to the base happiness
				levelUpType = createPokemonNo.getInt(18); // Sets levelUpType to the value of the specific row at column 18 (level_up_type)
				expGrowthFI.setText(Integer.toString(levelUpType)+ " points"); // Sets the label to the experience growth
				/** Sets eggGroups[0] to the value of the specific row at column 19 (egg_group_1) */
				eggGroups[0] = createPokemonNo.getString(19);
				eggGroup1.setText(eggGroups[0]); // Sets the label to egg group 1
				eggGroups[1] = createPokemonNo.getString(20); // Sets eggGroups[1] to the value of the specific row at column 20 (egg_group_2)
				if (eggGroups[1] != null) { // If the Pokémon has a second Egg Group...
					eggGroup2.setText(eggGroups[1]); // Sets the label to egg group 2
				} // End if
				abilities[0] = createPokemonNo.getString(21); // Sets abilities[0] to the value of the specific row at column 21 (ability_1)
				ability1FI.setText(abilities[0]); // Sets the label to ability 1
				abilities[1] = createPokemonNo.getString(22); // Sets abilities[1] to the value of the specific row at column 22 (ability_2)
				if (abilities[1] != null) { // If the Pokémon has a second ability...
					ability2FI.setText(abilities[1]); // Sets the label to ability 2
				} // End if
				/** Sets hiddenAbility to the value of the specific row at column 23 (hidden_ability) */
				hiddenAbility = createPokemonNo.getString(23);
				if (hiddenAbility != null) { // If the Pokémon has a hidden ability...
					hiddenAbilityFI.setText(hiddenAbility); // Sets the label to hidden ability
					if (abilities[1] == null) { // If the Pokémon does NOT have a second ability...
						hiddenAbilityFI.setLayoutX(598); // Sets the x location of hiddenAbilityFI to 598
						hiddenAbilityFI.setLayoutY(25); // Sets the y location of hiddenAbilityFI to 25
					} // End if
				} // End if
				if (pokemonName.equals("Rockruff")) { // If the Pokémon selected is Rockruff...
					rockruffAbilityFI.setVisible(true); // Shows Rockruff's 4th event-exclusive ability
				} // End if
				if (pokemonName.equals("Greninja")) { // If the Pokémon selected is Greninja
					ability2FI.setFont(fontBold); // Change the font of ability2FI to bold
					hiddenAbilityFI.setTextFill(Color.web("#FF0000")); // Changes the color of hiddenAbilityFI to red
				} // End if
				if (pokemonName.equals("Zygarde")) { // If the Pokémon selected is Zygarde
					ability2FI.setFont(fontBold); // Sets the font of ability2FI to bold
					ability2FI.setTextFill(Color.web("#FF0000")); // Sets the color of ability2FI to red
				} // End if
				/** Sets pokemonColor to the value of the specific row at column 24 (pokemon_color) */
				pokemonColor = createPokemonNo.getString(24);
				pokemonColorFI.setText(pokemonColor); // Sets the label to the color of the Pokémon
				if (pokemonColor.equals("Red")) { // If the Pokémon color is Red...
					pokemonColorFI.setTextFill(Color.web("#FF0000")); // Changes the color of pokemonColorFI to Red
				} // End if
				else if (pokemonColor.equals("Blue")) { // else if the Pokémon color is Blue...
					pokemonColorFI.setTextFill(Color.web("#0000FF")); // Changes the color of pokemonColorFI to Blue
				} // End else if
				else if (pokemonColor.equals("Yellow")) { // else if the Pokémon color is Yellow...
					pokemonColorFI.setTextFill(Color.web("#FFFF00")); // Changes the color of pokemonColorFI to Yellow
				} // End else if
				else if (pokemonColor.equals("Green")) { // else if the Pokémon color is Green...
					pokemonColorFI.setTextFill(Color.web("#008000")); // Changes the color of pokemonColorFI to Green
				} // End else if
				else if (pokemonColor.equals("Brown")) { // else if the Pokémon color is Brown...
					pokemonColorFI.setTextFill(Color.web("#A25A2A")); // Changes the color of pokemonColorFI to Brown
				} // End else if
				else if (pokemonColor.equals("Purple")) { // else if the Pokémon color is Purple...
					pokemonColorFI.setTextFill(Color.web("#800080")); // Changes the color of pokemonColorFI to Purple
				} // End else if
				else if (pokemonColor.equals("Gray")) { // else if the Pokémon color is Gray...
					pokemonColorFI.setTextFill(Color.web("#808080")); // Changes the color of pokemonColorFI to Gray
				} // End else if
				else if (pokemonColor.equals("White")) { // else if the Pokémon color is White...
					pokemonColorFI.setTextFill(Color.web("#FFFFFF")); // Changes the color of pokemonColorFI to White
				} // end else if 
				else if (pokemonColor.equals("Pink")) { // else if the Pokémon color is Pink...
					pokemonColorFI.setTextFill(Color.web("#FFC0CB")); // Changes the color of pokemonColorFI to Pink
				} // End else if
				else { // Else...
					pokemonColorFI.setTextFill(Color.web("#000000")); // Changes the color of pokemonColorFI to Black
				} // End else
				/** Sets pokemonHeight to the value of the specifc row at column 25 (pokemon_height) */
				pokemonHeight = createPokemonNo.getDouble(25);
				heightFillIn.setText(pokemonHeight+ " m"); // Sets the label to the Pokémon height in meters
				inches = pokemonHeight * 39.37; // This converts meters to inches
				feet = inches / 12; // This converts inches to feet
				Math.floor(feet); // Makes sure that it rounds down
				feetInt = (int) feet; // casts the feet to an int
				trueinches = inches % 12; // This gets inches
				Math.floor(trueinches); // Makes sure that it rounds down
				trueinchesInt = (int) trueinches; // casts inches to an int
				/** Sets the label to the height of the Pokémon in feet and inches */
				heightInchesFI.setText(Integer.toString(feetInt)+ "'" +Integer.toString(trueinchesInt)+"\"");
				/** Sets pokemonWeight to the value of the specific row at column 26 (pokemon_weight) */
				pokemonWeight = createPokemonNo.getDouble(26);
				weightFillIn.setText(pokemonWeight+ " kg"); // Sets the label to the weight of the Pokémon in kilograms
				pounds = pokemonWeight * 2.205; // Converts kilograms to pound
				/** Sets the label to the weight of the Pokémon in pounds */
				weightpoundsFI.setText(Double.toString(Math.round(pounds * 10) / 10.0)+ " lbs");
				/** Sets pokemonClass to the value of the specific row at column 27 (pokemon_type) */
				pokemonClass = createPokemonNo.getString(27);
				classFillIn.setText(pokemonClass+ " Pokémon"); // Sets the label to the Pokémon's classification
				/** Sets pokemonBiology to the value of the specific row at column 28 (pokemon_biology) */
				pokemonBiology = createPokemonNo.getString(28);
				/** Sets pokemonTrivia to the value of the specific row at column 29 (pokemon_trivia) */
				pokemonTrivia = createPokemonNo.getString(29);
				/** Sets pokemonOrigin to the value of the specific row at column 30 (pokemon_origin) */
				pokemonOrigin = createPokemonNo.getString(30);
				/** Sets pokemonNameOrigin to the value of the specific row at column 31 (pokemon_name_origin) */
				pokemonNameOrigin = createPokemonNo.getString(31);
				pokedexTA.setText("Biology:\n" +pokemonBiology+ "\n\nTrivia:\n" +pokemonTrivia+ "\n\nOrigin:\n" +pokemonOrigin+ 
						"\n\nName origin:\n" +pokemonNameOrigin); // Sets TextArea to the Pokémon's biology, trivia, name origin, and origin
				/** Sets gen7linkStr to the value of the specific row at column 37 (smogon_link_gen_7) */
				gen7linkStr = createPokemonNo.getString(37);
				gen7link.setText(gen7linkStr); // Sets the text to gen7link
				/** Sets gen8linkStr to the value of the specific row at column 38 (smogon_link_gen_8) */
				gen8linkStr = createPokemonNo.getString(38);
				gen8link.setText(gen8linkStr); // Sets the text to gen8link
				if (gen7linkStr == null) { // If the Pokémon does NOT have a gen 7 link...
					gen7lbl.setVisible(false); // Makes gen7lbl disappear
					gen8lbl.setLayoutX(16); // Sets the x location of gen8lbl to 16
					gen8lbl.setLayoutY(585); // Sets the y location of gen8lbl to 585
					gen8link.setLayoutX(54); // Sets the x location of gen8link to 54
					gen8link.setLayoutY(583); // Sets the y location of gen8link to 583
				} // End if
				if (gen8linkStr == null) { // If the Pokémon does NOT have a gen 8 link
					gen8lbl.setVisible(false); // Makes gen8lbl disappear
				} // End if
				/** Sets hasFemaleForm to the value of the specific row at column 32 (has_female_form) */
				hasFemaleForm = createPokemonNo.getBoolean(32);
				if (hasFemaleForm == true) { // If the Pokémon does NOT have a female form
					femaleCheck.setVisible(true); // Makes femaleCheck appear
				} // End if
				/** Sets pokemonEvolution to the value of the specific row at column 33 (evolution) */
				pokemonEvolution = createPokemonNo.getString(33);
				EvoTA.setText(pokemonEvolution); // Sets the TextArea to the evolution text for that Pokémon
				/** Sets hasOtherForms to the value of the specific row at column 34 (has_other_form) */
				hasOtherForms = createPokemonNo.getBoolean(34);
				if (hasOtherForms == false) { // If the Pokémon does NOT have other forms...
					formBox.setVisible(false); // Makes formBox disappear
				} // End if
				else { // Else...
					formBox.setVisible(true); // Makes formBox appear
					/** Sets otherFormIndex to the value of the specific row at column 35 (formList) */
					otherFormIndex = createPokemonNo.getInt(35);
					/** Sets the contents of the list with the forms */
					otherFormsList = FXCollections.observableArrayList(Arrays.asList(otherForms[otherFormIndex]));
					formBox.setItems(otherFormsList); // Sets the contents of the Form ComboBox with otherFormList
					/** Sets otherFormSelection to the value at the specific row at column 36 (other_form_name) */
					otherFormSelection = createPokemonNo.getInt(36);
					/** Sets the prompt text of the form ComboBox with what's selected at the otherForms 2D array */
					formBox.getSelectionModel().select(otherFormSelection);
				} // End else
			} // End else
			} // End of try block
		catch (SQLException e1) { // Catch block for SQLException, empty because there is no way of an exception happening
		} // End SQLException catch block
		catch (NumberFormatException e2) { // This catch happens if the user enters something other than a number
			final Stage notNumber = new Stage(); // Creates a new stage called notNumber
			notNumber.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
			notNumber.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
		    VBox dialogVbox = new VBox(20); // Creates a VBox called dialogVbox
		    dialogVbox.getChildren().add(new Text("Make sure the variable entered is a number")); // Adds everything to the dialogVbox
		    Scene dialogScene = new Scene(dialogVbox, 230, 20); // Creates a new scene called dialogScene
		    notNumber.setScene(dialogScene); // Sets the dialogScene onto the notNumber stage
		    notNumber.show(); // Shows the notNumber stage
		} // End of NumberFormatException catch block
	} // End of method
	
	/** Method that gets info about the Pokémon's other form/forme */
	public void getForm() {
		try { // try block for the getForm query statement
			/** Query statement to get the form of the Pokémon */
			queryForm = "SELECT * FROM pokemon WHERE other_form_name = \"" +otherFormsList.indexOf(formBox.getValue())+ "\" AND pokemon_name "
					+ "LIKE \"%" +pokemonFormNameHolder+"%\";";
			getForm = statement.executeQuery(queryForm); // Executes the queryForm statement
			cryButton.setVisible(true); // If the cry button isn't on yet, it turns it on now
			type2PicView.setImage(null); // If the newly selected Pokémon doesn't have a 2nd type
			ability2FI.setText(" "); // If the newly selected Pokémon doesn't have a 2nd ability
			hiddenAbilityFI.setText(" "); // If the newly selected Pokémon doesnt have a hidden ability
			eggGroup2.setText(" "); // If the newly selected Pokémon doesn't have a 2nd egg group
			HPIVsTF.setText("0"); // Sets the text of HPIVsTF to 0
			atkIVsTF.setText("0"); // Sets the text of atkIVsTF to 0
			defIVsTF.setText("0"); // Sets the text of defIVsTF to 0
			spDefIVsTF.setText("0"); // Sets the text of spDefIVsTF to 0
			spAtkIVsTF.setText("0"); // Sets the text of spAtkIVsTF to 0
			speIVsTF.setText("0"); // Sets the text of speIVsTF to 0
			HPEVsTF.setText("0"); // Sets the text of HPEVsTF to 0
			atkEVsTF.setText("0"); // Sets the text of atkEVsTF to 0
			defEVsTF.setText("0"); // Sets the text of defEVsTF to 0
			spAtkEVsTF.setText("0"); // Sets the text of spAtkEVsTF to 0
			spDefEVsTF.setText("0"); // Sets the text of spDefEVsTF to 0
			speEVsTF.setText("0"); // Sets the text of speEVsTF to 0
			totalBaseStatsTF.clear(); // Clears out totalBaseStatsTF
			totalIVsTF.clear(); // Clears out totalIVsTF
			totalEVsTF.clear(); // Clears out totalEVsTF
			hpStatTF.clear(); // Clears out hpStatTF
			atkStatTF.clear(); // Clears out atkStatTF
			defStatTF.clear(); // Clears out defStatTF
			spAtkStatTF.clear(); // Clears out spAtkStatTF
			spDefStatTF.clear(); // Clears out spDefStatTF
			speStatTF.clear(); // Clears out speStatTF
			totalStatsTF.clear(); // Clears out totalStatsTF
			getStatsButton.setVisible(true); // If it's the first Pokémon, it turns the Get Stats button on
			shinyCheck.setSelected(false); // If the shiny checkbox was on, it is now off
			femaleCheck.setSelected(false); // If the female checkbox was on, it is now off
			femaleCheck.setVisible(false); // Makes femaleCheck disappear
			ability1FI.setLayoutX(598); // Sets the x location of ability1FI to 598
			ability1FI.setLayoutY(8); // Sets the y location of ability1FI to 8
			ability2FI.setLayoutX(598); // Sets the x location of ability2FI to 598
			ability2FI.setLayoutY(25); // Sets the y location of ability2FI to 25
			hiddenAbilityFI.setLayoutX(598); // Sets the x location of hiddenAbilityFI to 598
			hiddenAbilityFI.setLayoutY(44); // Sets the y location of hiddenAbilityFI to 44
			gen7lbl.setVisible(true); // Makes the gen7lbl appear
			gen8lbl.setVisible(true); // Makes the gen8lbl appear
			gen8lbl.setLayoutX(16); // Sets the x location of gen8lbl2 to 16
			gen8lbl.setLayoutY(608); // Sets the y location of gen8lbl2 to 608
			gen8link.setLayoutX(54); // Sets the x location of gen8link2 to 54
			gen8link.setLayoutY(605); // Sets the y location of gen8link2 to 605
			rockruffAbilityFI.setVisible(false); // Makes Rockruff's fourth ability to appear
			hiddenAbilityFI.setTextFill(Color.web("#000000")); // Set the color of hiddenAbilityFI to black
			ability2FI.setFont(Font.font("System Bold", FontWeight.NORMAL, 12)); // Sets the font of ability2 to regular
			ability2FI.setTextFill(Color.web("#000000")); // Sets the color of ability2FI to black
			menuitem1.setDisable(false); // Enables menuitem1
			menuitem4.setDisable(false); // Enables menuitem4
			if(getForm.next()) { // If the getForm.next is true...
				natureCombobox.getSelectionModel().select(0); // Sets the nature in the stat calc to Hardy
				attacklbl.setTextFill(Color.web("#000000")); // Sets the color of attacklbl to black
				defenselbl.setTextFill(Color.web("#000000")); // Sets the color of defenselbl to black
				spAtklbl.setTextFill(Color.web("#000000")); // Sets the color of spAtklbl to black
				spDeflbl.setTextFill(Color.web("#000000")); // Sets the color of spDeflbl to black
				speedlbl.setTextFill(Color.web("#000000")); // Sets the color of speedlbl to black
				HPImageView.setImage(null); // Sets HPImageView to show nothing
				shinyCheck.setVisible(true); // Makes shinyCheck appear
				pokemonID = getForm.getInt(1); // Sets the pokemonID to the value of the specific row in column 1 (pokemon_id)
				/** Sets the pokemonNameID to the value of the specific row in column 2 (pokemon_name_id) */
				pokemonNameID = getForm.getString(2);
				pokemonName = getForm.getString(3); // Sets the pokemonName to the value of the specific row in column 3 (pokemon_name)
				pkmnNameFillIn.setText(pokemonName); // Sets the label to the name of the Pokémon
				pokemonsprite = new Image("sprites/" +pokemonID+ ".png"); // Sets pokemonsprite to the sprite of the Pokémon selected
				pokemonSpriteView.setImage(pokemonsprite); // Puts pokemonsprite into pokemonSpriteView
				cryLoc = "cries/" +pokemonNameID+ ".mp3"; // Sets cryLoc to the location of the Pokémon's cry
				pokemonCry = new Media(getClass().getResource(cryLoc).toExternalForm()); // Sets pokemonCry to the source of the Pokémon's cry
				cryPlayer = new AudioClip(pokemonCry.getSource()); // Puts pokemonCry into cryPlayer for the user to play
				baseStats[0] = getForm.getInt(4); // Sets baseStats[0] to the value of the specific row at column 4 (base_hp)
				baseHPTF.setText(Integer.toString(baseStats[0])); // Sets the TextField to baseStats[0]
				baseStats[1] = getForm.getInt(5); // Sets baseStats[1] to the value of the specific row at column 5 (base_attack)
				baseAtkTF.setText(Integer.toString(baseStats[1])); // Sets the TextField to baseStats[1]
				baseStats[2] = getForm.getInt(6); // Sets baseStats[2] to the value of the specific row at column 6 (base_defense)
				baseDefTF.setText(Integer.toString(baseStats[2])); // Sets the TextField to baseStats[2]
				baseStats[3] = getForm.getInt(7); // Sets baseStats[3] to the value of the specific row at column 7 (base_special_attack)
				baseSpAtkTF.setText(Integer.toString(baseStats[3])); // Sets the TextField to baseStats[3]
				baseStats[4] = getForm.getInt(8); // Sets baseStats[4] to the value of the specific row at column 8 (base_special_defense)
				baseSpDefTF.setText(Integer.toString(baseStats[4])); // Sets the TextField to baseStats[4]
				baseStats[5] = getForm.getInt(9); // Sets baseStats[5] to the value of the specific row at column 9 (base_speed)
				baseSpeTF.setText(Integer.toString(baseStats[5])); // Sets the TextField to baseStats[5]
				/** Sets type1pic to the location of the Pokémon's primary type */
				type1pic = new Image("types/" +getForm.getString(10)+ ".gif");
				type1PicView.setImage(type1pic); // Puts type1pic into type1PicView to view
				if (getForm.getString(11) != null) { // If the Pokémon has a second typing...
					/** Sets type2pic to the location of the Pokémon's secondary type */
					type2pic = new Image("types/" +getForm.getString(11)+ ".gif");
					type2PicView.setImage(type2pic); // Puts type2pic into type2PicView to view
				} // End if
				catchRate = getForm.getInt(12); // Sets catchRate to the value of the specific row at column 12 (catch_rate)
				catchRateFI.setText(Integer.toString(catchRate)); // Sets the label to the capture rate
				baseEXPYield = getForm.getInt(13); // Sets baseEXPYield to the value of the specific row at column 13 (base_exp_yield)
				BaseEXPFI.setText(Integer.toString(baseEXPYield)); // Sets the label the base EXP Yield
				EVYield = getForm.getString(14); // Sets EVYield to the value of the specific row at column 14 (ev_yield)
				evsEarnedFI.setText(EVYield); // Sets the label to the EV Yield
				genderRatio = getForm.getString(15); // Sets genderRatio to the value of the specific row at column 15 (gender_ratio) 
				genderRatioFillIn.setText(genderRatio); // Sets the label to the gender ratio
				eggCycle = getForm.getInt(16); // Sets eggCycle to the value of the specific row at column 16 (egg_cycle)
				eggCycleFI.setText(Integer.toString(eggCycle)); // Sets the label to the base egg steps
				baseFriendship = getForm.getInt(17); // Sets baseFriendship to the value of the specific row at column 17 (base_friendship)
				baseHapFI.setText(Integer.toString(baseFriendship)); // Sets the label to the base happiness
				levelUpType = getForm.getInt(18); // Sets levelUpType to the value of the specific row at column 18 (level_up_type)
				expGrowthFI.setText(Integer.toString(levelUpType)+ " points"); // Sets the label to the experience growth
				eggGroups[0] = getForm.getString(19); // Sets eggGroups[0] to the value of the specific row at column 19 (egg_group_1)
				eggGroup1.setText(eggGroups[0]); // Sets the label to egg group 1
				eggGroups[1] = getForm.getString(20); // Sets eggGroups[1] to the value of the specific row at column 20 (egg_group_2)
				if (eggGroups[1] != null) { // If the Pokémon has a second egg group...
					eggGroup2.setText(eggGroups[1]); // Sets the label to egg group 2
				} // end if
				abilities[0] = getForm.getString(21); // Sets abilities[0] to the value of the specific row at column 21 (ability_1)
				ability1FI.setText(abilities[0]); // Sets the label to ability 1
				abilities[1] = getForm.getString(22); // Sets abilities[1] to the value of the specific row at column 22 (ability_2)
				if (abilities[1] != null) { // If the Pokémon has a second ability...
					ability2FI.setText(abilities[1]); // Sets the label to ability 2
				} // End if
				hiddenAbility = getForm.getString(23); // Sets hiddenAbility to the value of the specific row at column 23 (hidden_ability)
				if (hiddenAbility != null) { // If the Pokémon has a hidden ability
					hiddenAbilityFI.setText(hiddenAbility); // Sets the ability to hidden ability
					if (abilities[1] == null) { // If the Pokémon does NOT have a second ability
						hiddenAbilityFI.setLayoutX(598); // Sets the x location of hiddenAbilityFI to 598
						hiddenAbilityFI.setLayoutY(25); // Sets the y location of hiddenAbilityFI to 25
					} // end if
				} // end if
				if (pokemonName.equals("Rockruff")) { // If the Pokémon selected is Rockruff...
					rockruffAbilityFI.setVisible(true); // Makes Rockruff's 4th ability appear
				} // End if
				if (pokemonName.equals("Greninja")) { // If the Pokémon selected is Greninja
					ability2FI.setFont(fontBold); // Change the font of ability2FI to bold
					hiddenAbilityFI.setTextFill(Color.web("#FF0000")); // Changes the color of hiddenAbilityFI to red
				} // End if
				if (pokemonName.equals("Zygarde")) { // If the Pokémon selected is Zygarde
					ability2FI.setFont(fontBold); // Sets the font of ability2FI to bold
					ability2FI.setTextFill(Color.web("#FF0000")); // Sets the color of ability2FI to red
				} // End if
				pokemonColor = getForm.getString(24); // Sets pokemonColor to the value of the specific row at column 24 (pokemon_color)
				pokemonColorFI.setText(pokemonColor); // Sets the label to the color of the Pokémon
				if (pokemonColor.equals("Red")) { // If the Pokémon's color is Red...
					pokemonColorFI.setTextFill(Color.web("#FF0000")); // Sets the color of pokemonColorFI to Red
				} // end if
				else if (pokemonColor.equals("Blue")) { // else if the Pokémon's color is Blue
					pokemonColorFI.setTextFill(Color.web("#0000FF")); // Sets the color of pokemonColorFI to Blue
				} // End else if
				else if (pokemonColor.equals("Yellow")) { // else if the Pokémon's color is Yellow
					pokemonColorFI.setTextFill(Color.web("#FFFF00")); // Sets the color of pokemonColorFI to Yellow
				} // End else if
				else if (pokemonColor.equals("Green")) { // else if the Pokémon's color is Green
					pokemonColorFI.setTextFill(Color.web("#008000")); // Sets the color of pokemonColorFI to Green
				} // End else if 
				else if (pokemonColor.equals("Brown")) { // else if the Pokémon's color is Brown
					pokemonColorFI.setTextFill(Color.web("#A25A2A")); // Sets the color of pokemonColorFI to Brown
				} // End else if 
				else if (pokemonColor.equals("Purple")) { // else if the Pokémon's color is Purple
					pokemonColorFI.setTextFill(Color.web("#800080")); // Sets the color of pokemonColorFI to Purple
				} // End else if
				else if (pokemonColor.equals("Gray")) { // else if the Pokémon's color is Gray
					pokemonColorFI.setTextFill(Color.web("#808080")); // Sets the color of pokemonColorFI to Gray
				} // End else if
				else if (pokemonColor.equals("White")) { // else if the Pokémon's color is White
					pokemonColorFI.setTextFill(Color.web("#FFFFFF")); // Sets the color of pokemonColorFI to White
				} // End else if
				else if (pokemonColor.equals("Pink")) { // else if the Pokémon's color is Pink
					pokemonColorFI.setTextFill(Color.web("#FFC0CB")); // Sets the color of pokemonColorFI to Pink
				} // End else if
				else { // Else...
					pokemonColorFI.setTextFill(Color.web("#000000")); // Sets the color of pokemonColorFI to Black
				} // End else
				pokemonHeight = getForm.getDouble(25); // Sets pokemonHeight to the value of the specifc row at column 25 (pokemon_height)
				heightFillIn.setText(pokemonHeight+ " m"); // Sets the label to the Pokémon height in meters
				inches = pokemonHeight * 39.37; // This converts meters to inches
				feet = inches / 12; // This converts inches to feet
				Math.floor(feet); // Makes sure that it rounds down
				feetInt = (int) feet; // casts the feet to an int
				trueinches = inches % 12; // This gets inches
				Math.floor(trueinches); // Makes sure that it rounds down
				trueinchesInt = (int) trueinches; // casts inches to an int
				/** Sets the label to the height of the Pokémon in feet and inches */
				heightInchesFI.setText(Integer.toString(feetInt)+ "'" +Integer.toString(trueinchesInt)+"\"");
				pokemonWeight = getForm.getDouble(26); // Sets pokemonWeight to the value of the specific row at column 26 (pokemon_weight)
				weightFillIn.setText(pokemonWeight+ " kg"); // Sets the label to the weight of the Pokémon in kilograms
				pounds = pokemonWeight * 2.205; // Converts kilograms to pound
				/** Sets the label to the weight of the Pokémon in pounds */
				weightpoundsFI.setText(Double.toString(Math.round(pounds * 10) / 10.0)+ " lbs");
				pokemonClass = getForm.getString(27); // Sets pokemonClass to the value of the specific row at column 27 (pokemon_type)
				classFillIn.setText(pokemonClass+ " Pokémon"); // Sets the label to the Pokémon's classification
				/** Sets pokemonBiology to the value of the specific row at column 28 (pokemon_biology) */
				pokemonBiology = getForm.getString(28);
				pokemonTrivia = getForm.getString(29); // Sets pokemonTrivia to the value of the specific row at column 29 (pokemon_trivia)
				pokemonOrigin = getForm.getString(30); // Sets pokemonOrigin to the value of the specific row at column 30 (pokemon_origin)
				/** Sets pokemonNameOrigin to the value of the specific row at column 31 (pokemon_name_origin) */
				pokemonNameOrigin = getForm.getString(31);
				pokedexTA.setText("Biology:\n" +pokemonBiology+ "\n\nTrivia:\n" +pokemonTrivia+ "\n\nOrigin:\n" +pokemonOrigin+ 
						"\n\nName origin:\n" +pokemonNameOrigin); // Sets TextArea to the Pokémon's biology, trivia, name origin, and origin
				hasFemaleForm = getForm.getBoolean(32); // Sets hasFemaleForm to the value of the specific row at column 32 (has_female_form)
				if (hasFemaleForm == true) { // If the Pokémon does NOT have a female form
					femaleCheck.setVisible(true); // Makes femaleCheck appear
				} // End if
				pokemonEvolution = getForm.getString(33); // Sets pokemonEvolution to the value of the specific row at column 33 (evolution)
				EvoTA.setText(pokemonEvolution); // Sets the TextArea to the evolution text for that Pokémon
				/** Sets gen7linkStr to the value of the specific row at column 37 (smogon_link_gen_7) */
				gen7linkStr = getForm.getString(37);
				gen7link.setText(gen7linkStr); // Sets the text of gen7link with gen7linkStr
				/** Sets gen8linkStr to the value of the specific row at column 38 (smogon_link_gen_8) */
				gen8linkStr = getForm.getString(38);
				gen8link.setText(gen8linkStr); // Sets the text of gen8link with gen8linkStr
				if (gen7linkStr == null) { // If the Pokémon does not have a gen 7 link...
					gen7lbl.setVisible(false); // Makes gen7lbl disappear
					gen8lbl.setLayoutX(16); // Sets the x location of gen8lbl to 16
					gen8lbl.setLayoutY(585); // Sets the y location of gen8lbl to 585
					gen8link.setLayoutX(54); // Sets the x location of gen8link to 54
					gen8link.setLayoutY(583); // Sets the y location of gen8link to 583
				} // End if
				if (gen8linkStr == null) { // If the Pokémon does NOT have a gen 8 link...
					gen8lbl.setVisible(false); // Makes gen8lbl disappear
				} // end if
			} // End if
		} // end of try block
		catch (SQLException e1) { // Catch block, empty because there is no way of an exception happening
		} // End of catch block
	} // End method
	
	/** Method that logs in the user */
	public void Login() {
		getLoginInfo = "SELECT * FROM login_info WHERE username = '" +usernameFI.getText().trim()+ "' AND password = '" 
	+passwordFI.getText().trim()+ "'"; // String for the query to read
		try { // Try block for the getLogin statement
			getLogin = statement.executeQuery(getLoginInfo); // Sends the statement to the database
			getLogin.next(); // Begins grabbing data for that specific username and password
			/** If the username and password have a match, it logs in the user. If not, throws an SQLException of it being correct  */
			if (usernameFI.getText() == getLogin.getString(4) && passwordFI.getText() == getLogin.getString(5)) throw new SQLException();
				final Stage successful = new Stage(); // Create a new stage called successful
				successful.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
				successful.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
                VBox successvbox = new VBox(); // Create a VBox called successvbox
                AnchorPane successAnchor = new AnchorPane(); // Creates an AnchorPane called successAnchor
                Button okButton = new Button("OK"); // Creates a button called okButton
                okButton.setLayoutX(81); // Sets the x location of okButton to 81
                okButton.setLayoutY(56); // Sets the y location of okButton to 56
                /** Label that says Thank you for logging in */
                Label oklbl = new Label("Thank you for logging in,\n" +getLogin.getString(2)+ " :)");
                oklbl.setLayoutX(32); // Sets the x location of oklbl to 32
                oklbl.setLayoutY(14); // Sets the y location of oklbl to 14
                successAnchor.getChildren().addAll(okButton,oklbl); // Adds everything to the successAnchor
                successvbox.getChildren().add(successAnchor); // Adds everything to the successvbox
                username = getLogin.getString(4); // Sets username to the value of the specific row at column 4 (username)
                password = getLogin.getString(5); // Sets password to the value of the specific row at column 5 (password)
                /** Sets hasDexPageSaved to the value of the specific row at column 6 (has_dex_page_saved) */
                hasDexPageSaved = getLogin.getBoolean(6);
                Scene dialogScene = new Scene(successvbox, 200, 100); // Creates a new scene called dialogScene
                successful.setScene(dialogScene); // Sets dialogScene scene to the successful stage
                successful.show(); // Shows the successful stage
                successful.setResizable(false); // Makes it so that successful cannot be resized
                okButton.setOnAction(e1 -> { // Event that closes the sucess screen and opens Pokédex Pro
                	successful.close(); // Closes the successful stage
                	loginStage.close(); // Closes the loginStage stage
                	mainStage.setTitle("Pokédex Pro"); // Sets the title of mainStage to Pokédex Pro
            		mainStage.setScene(scene); // Sets scene Scene to mainStage stage
            		mainStage.getIcons().add(new Image("icon/pokeballIcon.png"));
            		mainStage.show(); // Shows the mainStage
            		mainStage.setResizable(false); // makes sure that mainStage cannot be resized
            		if (hasDexPageSaved == true) { // If the true has saved a page from their last login...
            			/** String statement to get data for that dex page */
            			getDexPage = "SELECT * FROM pokemon JOIN login_info ON (id_of_page = pokemon_id) WHERE username = '" +username+ "'";
            			try { // Try block for getDex
            				/** Query statement to get the dex page */
            				getDex = statement.executeQuery(getDexPage);
            				cryButton.setVisible(true); // If the cry button isn't on yet, it turns it on now
            				type2PicView.setImage(null); // If the newly selected Pokémon doesn't have a 2nd type
            				ability2FI.setText(" "); // If the newly selected Pokémon doesn't have a 2nd ability
            				hiddenAbilityFI.setText(" "); // If the newly selected Pokémon doesnt have a hidden ability
            				eggGroup2.setText(" "); // If the newly selected Pokémon doesn't have a 2nd egg group
            				HPIVsTF.setText("0"); // Sets the text of HPIVsTF to 0
            				atkIVsTF.setText("0"); // Sets the text of atkIVsTF to 0
            				defIVsTF.setText("0"); // Sets the text of defIVsTF to 0
            				spDefIVsTF.setText("0"); // Sets the text of spDefIVsTF to 0
            				spAtkIVsTF.setText("0"); // Sets the text of spAtkIVsTF to 0
            				speIVsTF.setText("0"); // Sets the text of speIVsTF to 0
            				HPEVsTF.setText("0"); // Sets the text of HPEVsTF to 0
            				atkEVsTF.setText("0"); // Sets the text of atkEVsTF to 0
            				defEVsTF.setText("0"); // Sets the text of defEVsTF to 0
            				spAtkEVsTF.setText("0"); // Sets the text of spAtkEVsTF to 0
            				spDefEVsTF.setText("0"); // Sets the text of spDefEVsTF to 0
            				speEVsTF.setText("0"); // Sets the text of speEVsTF to 0
            				totalBaseStatsTF.clear(); // Clears out totalBaseStatsTF
            				totalIVsTF.clear(); // Clears out totalIVsTF
            				totalEVsTF.clear(); // Clears out totalEVsTF
            				hpStatTF.clear(); // Clears out hpStatTF
            				atkStatTF.clear(); // Clears out atkStatTF
            				defStatTF.clear(); // Clears out defStatTF
            				spAtkStatTF.clear(); // clears out spAtkTF
            				spDefStatTF.clear(); // Clears out spDefStatTF
            				speStatTF.clear(); // Clears out speStatTF
            				totalStatsTF.clear(); // Clears out totalStatsTF
            				ability1FI.setLayoutX(598); // Sets the x location of ability1FI to 598
            				ability1FI.setLayoutY(8); // Sets the y location of ability1FI to 8
            				ability2FI.setLayoutX(598); // Sets the x location of ability2FI to 598
            				ability2FI.setLayoutY(25); // Sets the y location of ability2FI to 25
            				hiddenAbilityFI.setLayoutX(598); // Sets the x location of hiddenAbilityFI to 598
            				hiddenAbilityFI.setLayoutY(44); // Sets the y location of hiddenAbilityFI to 44
            				getStatsButton.setVisible(true); // If it's the first Pokémon, it turns the Get Stats button on
            				shinyCheck.setSelected(false); // If the shiny checkbox was on, it is now off
            				femaleCheck.setSelected(false); // If the female checkbox was on, it is now off
            				femaleCheck.setVisible(false); // Makes femaleCheck disappear
            				hiddenAbilityFI.setTextFill(Color.web("#000000")); // Set the color of hiddenAbilityFI to black
            				ability2FI.setFont(Font.font("System Bold", FontWeight.NORMAL, 12)); // Sets the font of ability2 to regular
            				ability2FI.setTextFill(Color.web("#000000")); // Sets the color of ability2FI to black
            				gen7lbl.setVisible(true); // Makes the gen7lbl appear
            				gen8lbl.setLayoutX(16); // Sets the x location of gen8lbl2 to 16
            				gen8lbl.setLayoutY(608); // Sets the y location of gen8lbl2 to 608
            				gen8link.setLayoutX(54); // Sets the x location of gen8link2 to 54
            				gen8link.setLayoutY(605); // Sets the y location of gen8link2 to 605
            				menuitem1.setDisable(false); // Enables menuitem1
            				menuitem4.setDisable(false); // Enables menuitem4
            				getDex.next(); // Begins grabbing data for that specific Pokédex page
            				natureCombobox.getSelectionModel().select(0); // Sets the nature in the stat calc to Hardy
        					attacklbl.setTextFill(Color.web("#000000")); // Sets the color of attacklbl to black
        					defenselbl.setTextFill(Color.web("#000000")); // Sets the color of defenselbl to black
        					spAtklbl.setTextFill(Color.web("#000000")); // Sets the color of spAtklbl to black
        					spDeflbl.setTextFill(Color.web("#000000")); // Sets the color of spDeflbl to black
        					speedlbl.setTextFill(Color.web("#000000")); // Sets the color of speedlbl to black
        					HPImageView.setImage(null); // Sets HPImageView to show nothing
        					shinyCheck.setVisible(true); // Makes shinyCheck appear
            				pokemonID = getDex.getInt(1); // Sets pokemonID to the value of that specific row in column 1 (pokemon_id)
            				pokemonNoBox.setText(Integer.toString(pokemonID)); // Sets the TextField to the ID of the pokémon
            				/** Sets the pokemonNameID to the value of the specific row in column 2 (pokemon_name_id) */
            				pokemonNameID = getDex.getString(2);
            				/** Sets the pokemonName to the value of the specific row in column 3 (pokemon_name) */
            				pokemonName = getDex.getString(3);
            				pkmnNameFillIn.setText(pokemonName); // Sets the label to the name of the Pokémon
            				pokemonFormNameHolder = pokemonName; // Sets pokemonFormNamHolder with whatever is in pokemonName
            				pokemonNameBox.setPromptText(pokemonName); // Sets the text of the pokemonNameBox to name of the Pokémon
            				pokemonsprite = new Image("sprites/" +pokemonID+ ".png"); // Sets pokemonsprite to the sprite of the Pokémon selected
            				pokemonSpriteView.setImage(pokemonsprite); // Puts pokemonsprite into pokemonSpriteView to view
            				cryLoc = "cries/" +pokemonNameID+ ".mp3"; // Stores the location of mp3 file
            				pokemonCry = new Media(getClass().getResource(cryLoc).toExternalForm()); // Sets pokemonCry to the source of the Pokémon's cry
            				/** Puts pokemonCry into the cryPlayer for the user to listen to */
            				cryPlayer = new AudioClip(pokemonCry.getSource());
            				baseStats[0] = getDex.getInt(4); // Sets baseStats[0] to the value of the specific row at column 4 (base_hp)
            				baseHPTF.setText(Integer.toString(baseStats[0])); // Sets the TextField to baseStats[0]
            				baseStats[1] = getDex.getInt(5); // Sets baseStats[1] to the value of the specific row at column 5 (base_attack)
            				baseAtkTF.setText(Integer.toString(baseStats[1])); // Sets the TextField to baseStats[1]
            				baseStats[2] = getDex.getInt(6); // Sets baseStats[2] to the value of the specific row at column 6 (base_defense)
            				baseDefTF.setText(Integer.toString(baseStats[2])); // Sets the TextField to baseStats[2]
            				/** Sets baseStats[3] to the value of the specific row at column 7 (base_special_attack) */
            				baseStats[3] = getDex.getInt(7);
            				baseSpAtkTF.setText(Integer.toString(baseStats[3])); // Sets the TextField to baseStats[3]
            				/** Sets baseStats[4] to the value of the specific row at column 8 (base_special_defense) */
            				baseStats[4] = getDex.getInt(8);
            				baseSpDefTF.setText(Integer.toString(baseStats[4])); // Sets the TextField to baseStats[4]
            				baseStats[5] = getDex.getInt(9); // Sets baseStats[5] to the value of the specific row at column 9 (base_speed)
            				baseSpeTF.setText(Integer.toString(baseStats[5])); // Sets the TextField to baseStats[5]
            				/** Sets type1Pic to the value of the specific row at column 10 */
            				type1pic = new Image("types/" +getDex.getString(10)+ ".gif");
            				type1PicView.setImage(type1pic); // Puts type1pic into type1PicView to view
            				if (getDex.getString(11) != null) { // If the Pokémon has a second type...
            					/** Sets type2pic to the location of the Pokémon's secondary type */
            					type2pic = new Image("types/" +getDex.getString(11)+ ".gif");
            					type2PicView.setImage(type2pic); // Puts type2pic into type2PicView to view
            				} // End if
            				catchRate = getDex.getInt(12); // Sets catchRate to the value of the specific row at column 12 (catch_rate)
            				catchRateFI.setText(Integer.toString(catchRate)); // Sets the label to the capture rate
            				/** Sets baseEXPYield to the value of the specific row at column 13 (base_exp_yield) */
            				baseEXPYield = getDex.getInt(13);
            				BaseEXPFI.setText(Integer.toString(baseEXPYield)); // Sets the label the base EXP Yield
            				EVYield = getDex.getString(14); // Sets EVYield to the value of the specific row at column 14 (ev_yield)
            				evsEarnedFI.setText(EVYield); // Sets the label to the EV Yield
            				/** Sets genderRatio to the value of the specific row at column 15 (gender_ratio) */
            				genderRatio = getDex.getString(15);
            				genderRatioFillIn.setText(genderRatio); // Sets the label to the gender ratio
            				eggCycle = getDex.getInt(16); // Sets eggCycle to the value of the specific row at column 16 (egg_cycle)
            				eggCycleFI.setText(Integer.toString(eggCycle)); // Sets the label to the base egg steps
            				/** Sets baseFriendship to the value of the specific row at column 17 (base_friendship) */
            				baseFriendship = getDex.getInt(17);
            				baseHapFI.setText(Integer.toString(baseFriendship)); // Sets the label to the base happiness
            				/** Sets levelUpType to the value of the specific row at column 18 (level_up_type) */
            				levelUpType = getDex.getInt(18);
            				expGrowthFI.setText(Integer.toString(levelUpType)+ " points"); // Sets the label to the experience growth
            				/** Sets eggGroups[0] to the value of the specific row at column 19 (egg_group_1) */
            				eggGroups[0] = getDex.getString(19);
            				eggGroup1.setText(eggGroups[0]); // Sets the label to egg group 1
            				/** Sets eggGroups[1] to the value of the specific row at column 20 (egg_group_2) */
            				eggGroups[1] = getDex.getString(20);
            				if (eggGroups[1] != null) { // If the Pokémon has a second ability
            					eggGroup2.setText(eggGroups[1]); // Sets the label to egg group 2
            				} // End if
            				/** Sets abilities[0] to the value of the specific row at column 21 (ability_1) */
            				abilities[0] = getDex.getString(21);
            				ability1FI.setText(abilities[0]); // Sets the label to ability 1
            				/** Sets abilities[1] to the value of the specific row at column 22 (ability_2) */
            				abilities[1] = getDex.getString(22);
            				if (abilities[1] != null) { // If the Pokémon has a second ability
            					ability2FI.setText(abilities[1]); // Sets the label to ability 2
            				} // End if
            				/** Sets hiddenAbility to the value of the specific row at column 23 (hidden_ability) */
            				hiddenAbility = getDex.getString(23);
            				if (hiddenAbility != null) { // If the Pokémon has a hidden ability
            					hiddenAbilityFI.setText(hiddenAbility); // Sets the label to hidden ability
            					if (abilities[1] == null) { // If the Pokémon does NOT have a second ability
            						hiddenAbilityFI.setLayoutX(598); // Sets the x location of hiddenAbilityFI to 598
            						hiddenAbilityFI.setLayoutY(25); // Sets the y location of hiddenAbilityFI to 25
            					} // End if
            				} // End if
            				if (pokemonName.equals("Rockruff")) { // If the Pokémon selected is Rockruff...
            					rockruffAbilityFI.setVisible(true); // Makes Rockruff's 4th ability appear
            				} // End if
            				if (pokemonName.equals("Greninja")) { // If the Pokémon selected is Greninja
            					ability2FI.setFont(fontBold); // Change the font of ability2FI to bold
            					hiddenAbilityFI.setTextFill(Color.web("#FF0000")); // Changes the color of hiddenAbilityFI to red
            				} // End if
            				if (pokemonName.equals("Zygarde")) { // If the Pokémon selected is Zygarde
            					ability2FI.setFont(fontBold); // Sets the font of ability2FI to bold
            					ability2FI.setTextFill(Color.web("#FF0000")); // Sets the color of ability2FI to red
            				} // End if
            				/** Sets pokemonColor to the value of the specific row at column 24 (pokemon_color) */
            				pokemonColor = getDex.getString(24);
            				pokemonColorFI.setText(pokemonColor); // Sets the label to the color of the Pokémon
            				if (pokemonColor.equals("Red")) { // If the Pokémon's color is Red...
            					pokemonColorFI.setTextFill(Color.web("#FF0000")); // Sets the color of pokemonColorFI to Red
            				} // End if
            				else if (pokemonColor.equals("Blue")) { // Else if the Pokémon's color is Blue
            					pokemonColorFI.setTextFill(Color.web("#0000FF")); // Sets the color of pokemonColorFI to Blue
            				} // End else if
            				else if (pokemonColor.equals("Yellow")) { // Else if the Pokémon's color is Yellow
            					pokemonColorFI.setTextFill(Color.web("#FFFF00")); // Sets the color of pokemonColorFI to Yellow
            				} // End else if
            				else if (pokemonColor.equals("Green")) { // Else if the Pokémon's color is Green
            					pokemonColorFI.setTextFill(Color.web("#008000")); // Sets the color of pokemonColorFI to Green
            				} // End else if
            				else if (pokemonColor.equals("Brown")) { // Else if the Pokémon's color is Brown
            					pokemonColorFI.setTextFill(Color.web("#A25A2A")); // Sets the color of pokemonColorFI to Brown
            				} // End else if
            				else if (pokemonColor.equals("Purple")) { // Else if the Pokémon's color is Purple
            					pokemonColorFI.setTextFill(Color.web("#800080")); // Sets the color of pokemonColorFI to Purple
            				} // End else if
            				else if (pokemonColor.equals("Gray")) { // Else if the Pokémon's color is Gray
            					pokemonColorFI.setTextFill(Color.web("#808080")); // Sets the color of pokemonColorFI to Gray
            				} // End else if
            				else if (pokemonColor.equals("White")) { // Else if the Pokémon's color is White
            					pokemonColorFI.setTextFill(Color.web("#FFFFFF")); // Sets the color of pokemonColorFI to White
            				} // End else if
            				else if (pokemonColor.equals("Pink")) { // Else if the Pokémon's color is Pink
            					pokemonColorFI.setTextFill(Color.web("#FFC0CB")); // Sets the color of pokemonColorFI to Pink
            				} // End else if
            				else { // Else...
            					pokemonColorFI.setTextFill(Color.web("#000000")); // Sets the color of pokemonColorFI to Black
            				} // End else
            				/** Sets pokemonHeight to the value of the specifc row at column 25 (pokemon_height) */
            				pokemonHeight = getDex.getDouble(25);
            				heightFillIn.setText(pokemonHeight+ " m"); // Sets the label to the Pokémon height in meters
            				inches = pokemonHeight * 39.37; // This converts meters to inches
            				feet = inches / 12; // This converts inches to feet
            				Math.floor(feet); // Makes sure that it rounds down
            				feetInt = (int) feet; // casts the feet to an int
            				trueinches = inches % 12; // This gets inches
            				Math.floor(trueinches); // Makes sure that it rounds down
            				trueinchesInt = (int) trueinches; // casts inches to an int
            				/** Sets the label to the height of the Pokémon in feet and inches */
            				heightInchesFI.setText(Integer.toString(feetInt)+ "'" +Integer.toString(trueinchesInt)+"\"");
            				/** Sets pokemonWeight to the value of the specific row at column 26 (pokemon_weight) */
            				pokemonWeight = getDex.getDouble(26);
            				weightFillIn.setText(pokemonWeight+ " kg"); // Sets the label to the weight of the Pokémon in kilograms
            				pounds = pokemonWeight * 2.205; // Converts kilograms to pound
            				/** Sets the label to the weight of the Pokémon in pounds */
            				weightpoundsFI.setText(Double.toString(Math.round(pounds * 10) / 10.0)+ " lbs");
            				/** Sets pokemonClass to the value of the specific row at column 27 (pokemon_type) */
            				pokemonClass = getDex.getString(27);
            				classFillIn.setText(pokemonClass+ " Pokémon"); // Sets the label to the Pokémon's classification
            				/** Sets pokemonBiology to the value of the specific row at column 28 (pokemon_biology) */
            				pokemonBiology = getDex.getString(28);
            				/** Sets pokemonTrivia to the value of the specific row at column 29 (pokemon_trivia) */
            				pokemonTrivia = getDex.getString(29);
            				/** Sets pokemonOrigin to the value of the specific row at column 30 (pokemon_origin) */
            				pokemonOrigin = getDex.getString(30);
            				/** Sets pokemonNameOrigin to the value of the specific row at column 31 (pokemon_name_origin) */
            				pokemonNameOrigin = getDex.getString(31);
            				/** Sets TextArea to the Pokémon's biology, trivia, name origin, and origin */
            				pokedexTA.setText("Biology:\n" +pokemonBiology+ "\n\nTrivia:\n" +pokemonTrivia+ "\n\nOrigin:\n" +pokemonOrigin+ 
            						"\n\nName origin:\n" +pokemonNameOrigin);
            				/** Sets gen7linkStr to the value of the specific row at column 37 (smogon_link_gen_7) */
            				gen7linkStr = getDex.getString(37);
            				gen7link.setText(gen7linkStr); // Sets the text of gen7link to gen7linkStr
            				/** Sets gen8linkStr to the value of the specific row at column 38 (smogon_link_gen_8) */
            				gen8linkStr = getDex.getString(38);
            				gen8link.setText(gen8linkStr); // Sets the text of gen8link to gen8linkStr
            				if (gen7linkStr == null) { // If the Pokémon does NOT have a gen 7 link...
            					gen7lbl.setVisible(false); // Makes gen7lbl disappear
            					gen8lbl.setLayoutX(16); // Sets the x location of gen8lbl to 16
            					gen8lbl.setLayoutY(585); // Sets the y location of gen8lbl to 560
            					gen8link.setLayoutX(54); // Sets the x location of gen8link to 54
            					gen8link.setLayoutY(583); // Sets the y location of gen8link to 557
            				} // End if
            				if (gen8linkStr == null) { // If the Pokémon does NOT have a gen 8 link...
            					gen8lbl.setVisible(false); // Makes gen8lbl disappear
            				} // End if
            				/** Sets hasFemaleForm to the value of the specific row at column 32 (has_female_form) */
            				hasFemaleForm = getDex.getBoolean(32);
            				if (hasFemaleForm == true) { // If the Pokémon does NOT have a female form
            					femaleCheck.setVisible(true); // Makes femaleCheck appear
            				} // End if
            				/** Sets pokemonEvolution to the value of the specific row at column 33 (evolution) */
            				pokemonEvolution = getDex.getString(33);
            				EvoTA.setText(pokemonEvolution); // Sets the TextArea to the evolution text for that Pokémon
            				/** Sets hasOtherForms to the value of the specific row at column 34 (has_other_form) */
            				hasOtherForms = getDex.getBoolean(34);
            				if (hasOtherForms == false) { // If the Pokémon does NOT have other forms
            					formBox.setVisible(false); // Makes formBox disappear
            				} // End if
            				else { // Else...
            					formBox.setVisible(true); // Makes formBox appear
            					/** Sets otherFormIndex to the value of the specific row at column 35 (formList) */
            					otherFormIndex = getDex.getInt(35);
            					/** Sets the contents of the list with the forms */
            					otherFormsList = FXCollections.observableArrayList(Arrays.asList(otherForms[otherFormIndex]));
            					formBox.setItems(otherFormsList); // Sets the contents of the Form ComboBox with otherFormList
            					/** Sets otherFormSelection to the value at the specific row at column 36 (other_form_name) */
            					otherFormSelection = getDex.getInt(36);
            					/** Sets the prompt text of the form ComboBox with what's selected at the otherForms 2D array */
            					formBox.getSelectionModel().select(otherFormSelection);
            				} // End else
            			} // End of try block
            			catch (SQLException e2) { // Catch block, empty because there is no way of an exception happening
						} // End of catch block
            		} // End if
                }); // End okButton event
                
                successful.setOnCloseRequest(e1 -> { // When the success page is closed with the x button, it opens Pokédex Pro
                	successful.close(); // closes the success page
                	loginStage.close(); // closes the login page
                	mainStage.setTitle("Pokédex Pro"); // Sets the title of mainStage to Pokédex Pro
            		mainStage.setScene(scene); // Sets scene Scene to mainStage stage
            		mainStage.getIcons().add(new Image("icon/pokeballIcon.png"));
            		mainStage.show(); // Shows mainStage
            		mainStage.setResizable(false); // makes sure that mainStage cannot be resized
                }); // Ends successful close event
		} catch (SQLException e1) { // If the username or password is incorrect, it pops up saying so
			final Stage ohno = new Stage(); // Create a new stage called ohno
			ohno.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
			ohno.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
            VBox ohnovbox = new VBox(); // Create a VBox called ohnovbox
            AnchorPane ohnoAnchor = new AnchorPane(); // Creates an AnchorPane called ohnoAnchor
            Button okButton = new Button("OK"); // Creates a new button called okButton
            okButton.setLayoutX(81); // Sets the x location of okButton to 81
            okButton.setLayoutY(56); // Sets the y location of okButton to 56
            Label oklbl = new Label("Incorrect username/password"); // Label that says Incorrect username/password
            oklbl.setLayoutX(32); // Sets the x location of oklbl to 32
            oklbl.setLayoutY(14); // Sets the y location of oklbl to 14
            ohnoAnchor.getChildren().addAll(okButton,oklbl); // Adds everything to the ohnoAnchor
            ohnovbox.getChildren().add(ohnoAnchor); // Adds everything to the ohnovbox
            Scene dialogScene = new Scene(ohnovbox, 225, 100); // Creates a new scene called dialogScene
            ohno.setScene(dialogScene); // Sets the dialogScene scene to ohno stage
            ohno.show(); // Shows ohno stage
            ohno.setResizable(false); // Makes sure that ohno can't be resized
            okButton.setOnAction(e2 -> { // Event that closes ohno
            	ohno.close(); // Closes ohno
            }); // End okButton event
		} // End of catch block
	} // End of method
	
	/** Method that opens up the Create Account window */
	public void openCAWindow() {
		loginStage.close(); // Closes loginStage page
		firstNameFI.clear(); // Clears out firstNameFI
		lastNameFI.clear(); // Clears out lastNameFI
		createUNFI.clear(); // Clears out createUNFI
		createPWFI.clear(); // Clears out createPWFI
		createStage.setScene(createAccountScene); // Sets the createAccountScene scene to createStage stage
		createStage.setTitle("Create Account"); // Sets the title of createStage to Create Account
		createStage.show(); // Shows createStage stage
		createStage.setResizable(false); // makes sure that createStage can't be resized
	} // End of method
	
	/** Method that creates the user's account */
	public void CreateAccount() {
		/**  If the user inputs nothing in the username and password, or if any of the fields have ", it says you can't do that */
		if (createUNFI.getText().equals("") || createPWFI.getText().equals("") || firstNameFI.getText().equals("\"") || 
				lastNameFI.getText().equals("\"") || createUNFI.getText().equals("\"") || createPWFI.getText().equals("\"")) {
			final Stage ohno = new Stage(); // Creates a stage called ohno
			ohno.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
			ohno.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
            VBox ohnovbox = new VBox(); // Creates a new VBox called ohnovbox
            AnchorPane ohnoAnchor = new AnchorPane(); // Creates an AnchorPane called ohnoAnchor
            Button okButton = new Button("OK"); // Create a button called okButton
            okButton.setLayoutX(81); // Sets the x location of okButton to 81
            okButton.setLayoutY(56); // Sets the y location of okButton to 56
            Label oklbl = new Label("Invalid characters inputted"); // Label called Invalid characters inputted
            oklbl.setLayoutX(32); // Sets the x location of oklbl to 32
            oklbl.setLayoutY(14); // Sets the y location of oklbl to 14
            ohnoAnchor.getChildren().addAll(okButton,oklbl); // Adds everything to ohnoAnchor
            ohnovbox.getChildren().add(ohnoAnchor); // Adds everything to ohnovbox
            Scene dialogScene = new Scene(ohnovbox, 225, 100); // Creates scene called dialogScene
            ohno.setScene(dialogScene); // Sets dialogScene into ohno
            ohno.show(); // Shows ohno stage
            ohno.setResizable(false); // Makes sure that ohno can't be resized
            okButton.setOnAction(e2 -> { // Event that closes the ohno stage
            	ohno.close(); // Closes ohno stage
            }); // Ends okButton event
		} // End if
            else { // Else
		createAccountStatement = "INSERT INTO login_info (first_name, last_name, username, password) VALUES ('" 
	+firstNameFI.getText().trim()+ "','" +lastNameFI.getText().trim()+ "','" +createUNFI.getText().trim()+ "','" 
				+createPWFI.getText().trim()+ "')";
		try { // Try block to create an account
			statement.execute(createAccountStatement); // Statement that's sent to database to make their account
			final Stage accountMade = new Stage(); // Creates a new stage called accountMade
			accountMade.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
			accountMade.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
            VBox confirm = new VBox(); // Creates a VBox named confirm
            AnchorPane confirmAnchor = new AnchorPane(); // Creates an AnchorPane called confirmAnchor
            Button okButton = new Button("OK"); // Creates a button called okButton
            okButton.setLayoutX(93); // Sets the x location of okButton to 93
            okButton.setLayoutY(27); // Sets the y location of okButton to 27
            Label oklbl = new Label("Account successfully created :)"); // Label that says Account successfully created :)
            oklbl.setLayoutX(28); // Sets the x location of oklbl to 28
            oklbl.setLayoutY(5); // Sets the y location of oklbl to 5
            confirmAnchor.getChildren().addAll(okButton,oklbl); // Adds everything to confirmAnchor
            confirm.getChildren().add(confirmAnchor); // Adds everything to confirm
            Scene dialogScene = new Scene(confirm, 218, 68); // Creates a new scene called dialogScene
            accountMade.setScene(dialogScene); // Sets dialogScene scene to accountMade stage
            accountMade.show(); // Shows accountMade
            accountMade.setResizable(false); // Makes sure that accountMade can't be resized
            accountMade.setOnCloseRequest(e -> { // If the user uses the x button to close the dialog box
            	accountMade.close(); // Closes accountMade
            	createStage.close(); // Closes createStage
            	usernameFI.clear(); // Clears username field
            	passwordFI.clear(); // Clears password field
            	loginStage.show(); // Opens loginStage
            }); // end of event
            okButton.setOnAction(e1 -> { // okButton event that goes back to the login
            	accountMade.close(); // Closes accountMade
            	createStage.close(); // Closes createStage
            	usernameFI.clear(); // Clears username field
            	passwordFI.clear(); // Clears password field
            	loginStage.show(); // Opens loginStage
            }); // End okButton event
		} // end of try block
		/** Catch block, happens when a user tries to input a username that's being used currently */
		catch (SQLIntegrityConstraintViolationException e1) {
			final Stage usernameTaken = new Stage(); // Creates a stage called usernameTake
			usernameTaken.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
			usernameTaken.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
            VBox taken = new VBox(); // Creates a VBox named taken
            AnchorPane takenAnchor = new AnchorPane(); // Creates an AnchorPane called takenAnchor
            Button okButton = new Button("OK"); // Creates a button called okButton
            okButton.setLayoutX(93); // Sets the x location of okButton to 93
            okButton.setLayoutY(27); // Sets the y location of okButton of 27
            Label oklbl = new Label("Username taken :("); // Label that says Username taken :(
            oklbl.setLayoutX(58); // Sets the x location of oklbl to 58
            oklbl.setLayoutY(5); // Sets the y location of oklbl to 5
            takenAnchor.getChildren().addAll(okButton,oklbl); // Adds everything to takenAnchor
            taken.getChildren().add(takenAnchor); // Adds everything to taken
            Scene dialogScene = new Scene(taken, 218, 68); // Creates a scene called dialogScene
            usernameTaken.setScene(dialogScene); // Sets dialogScene scene to usernameTaken stage
            usernameTaken.show(); // Shows usernameTaken stage
            usernameTaken.setResizable(false); // Makes sure that usernameTaken can't be resized
            okButton.setOnAction(e2 -> { // Event that closes usernameTaken stage
            	usernameTaken.close(); // Closes usernameTaken
            }); // Ends okButton event
		} // End catch block
		catch (MysqlDataTruncation e2) { // Checks to make sure the username and password are not more than 20 characters
				final Stage tooLong = new Stage(); // Creates a stage called tooLong
				tooLong.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
				tooLong.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
                VBox tooLongBox = new VBox(); // Creates a VBox called tooLongBox
                AnchorPane tooLongAnchor = new AnchorPane(); // Creates an AnchorPane called tooLongAnchor
                Button okButton = new Button("OK"); // Creates a button called okButton
                okButton.setLayoutX(150); // Sets the x location of okButton to 150
                okButton.setLayoutY(25); // Sets the y location of okButton to 25
                /** Label that says Username and password cannot be more than 20 characters */
                Label oklbl = new Label("Username and password cannot be more than 20 characters");
                oklbl.setLayoutX(4); // Sets the x location of oklbl to 4
                oklbl.setLayoutY(5); // Sets the y location of oklbl to 5
                tooLongAnchor.getChildren().addAll(okButton,oklbl); // Adds everything to tooLongAnchor
                tooLongBox.getChildren().add(tooLongAnchor); // Adds everything to tooLongBox
                Scene dialogScene = new Scene(tooLongBox, 326, 68); // Creates scene called dialogScene
                tooLong.setScene(dialogScene); // Sets dialogScene scene to tooLong stage
                tooLong.show(); // Shows tooLong stage
                tooLong.setResizable(false); // Makes sure that tooLong window can't be resized
                okButton.setOnAction(e1 -> { // Event that closes tooLong
                	tooLong.close(); // Closes tooLong stage
                }); // Ends okButton event
		} // End of catch block
		catch (SQLException e2) { // Catch block, empty because there is no way of an exception happening
		} // End of catch block
            } // End else
	} // End of method
	
	/** Method that saves the dex page of the Pokémon selected */
	public void saveDexPage() {
		if (pokemonID > 890) { // If the user tries to save a Pokémon whose ID is greater than 890, popup says you can't do that
			final Stage tooLong = new Stage(); // Creates a stage called tooLong
			tooLong.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
			tooLong.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
            VBox tooLongBox = new VBox(); // Creates a VBox called tooLongBox
            AnchorPane tooLongAnchor = new AnchorPane(); // Creates an AnchorPane called tooLongAnchor
            Button okButton = new Button("OK"); // Creates a button called okButton
            okButton.setLayoutX(150); // Sets the x location of okButton to 150
            okButton.setLayoutY(25); // Sets the y location of okButton to 25
            Label oklbl = new Label("Page cannot be saved"); // Label that says Page cannot be saved
            oklbl.setLayoutX(4); // Sets the x locaton of oklbl to 4
            oklbl.setLayoutY(5); // Sets the y location of oklbl to 5
            tooLongAnchor.getChildren().addAll(okButton,oklbl); // Adds everything to tooLongAnchor
            tooLongBox.getChildren().add(tooLongAnchor); // Adds everything to tooLongBox
            Scene dialogScene = new Scene(tooLongBox, 326, 68); // Creates a scene called dialogScene
            tooLong.setScene(dialogScene); // Sets dialogScene scene to tooLong stage
            tooLong.show(); // Shows tooLong stage
            tooLong.setResizable(false); // Makes sure that tooLong stage can't be resized
            okButton.setOnAction(e1 -> { // Event that closes tooLong stage
            	tooLong.close(); //  Close the tooLong stage
            }); // End okButton event
		} // End if
		else { // Else
		hasDexPageSaved = true; // Sets hasDexPageSaved to true
		saveDexPage = "UPDATE login_info SET has_dex_page_saved = " +hasDexPageSaved+ ", id_of_page = " +pokemonID+ " WHERE username = '" 
		+username+ "'"; // String that will execute to the database
		try { // Try block that updates the user's info to save a Pokédex page
			statement.execute(saveDexPage); // Statement that executes to the database
			final Stage tooLong = new Stage(); // Creates a stage called tooLong
			tooLong.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
			tooLong.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
            VBox tooLongBox = new VBox(); // Creates a VBox called tooLongBox
            AnchorPane tooLongAnchor = new AnchorPane(); // Creates an AnchorPane called tooLongAnchor
            Button okButton = new Button("OK"); // Creates a button called okButton
            okButton.setLayoutX(150); // Sets the x location of okButton to 150
            okButton.setLayoutY(25); // Sets the y location of okButton to 25
            Label oklbl = new Label("Page has been saved"); // Label that says Page has been saved
            oklbl.setLayoutX(4); // Sets the x location of oklbl to 4
            oklbl.setLayoutY(5); // Sets the y location of oklbl to 5
            tooLongAnchor.getChildren().addAll(okButton,oklbl); // Adds everything to tooLongAnchor
            tooLongBox.getChildren().add(tooLongAnchor); // Adds everything to tooLongBox
            Scene dialogScene = new Scene(tooLongBox, 326, 68); // Creates a scene called dialogScene
            tooLong.setScene(dialogScene); // Sets the dialogScene scene into tooLong stage
            tooLong.show(); // Shows tooLong stage
            tooLong.setResizable(false); // Makes sure that tooLong stage can't be resized
            okButton.setOnAction(e1 -> { // Button that closes tooLong
            	tooLong.close(); // closes tooLong stage
            }); // Ends okButton event
		} // end of try block
		catch (SQLException e1) { // Catch block, empty because there is no way of an exception happening
		} // End of catch block
		} // End else
	} // End of method
	
	/** Method that deletes the saved page that the user could have */
	public void clearDexPage() {
		hasDexPageSaved = false; // Sets hasDexPageSaved to false
		/** String that will be executed to the database */
		clearDexPage = "UPDATE login_info SET has_dex_page_saved = " +hasDexPageSaved+ ", id_of_page = 0 WHERE username = '" +username+ "'";
		try { // Try block that clears any saved dex page
			statement.execute(clearDexPage); // Statement that executes the clearDexPage
			final Stage tooLong = new Stage(); // Creates a stage called tooLong
			tooLong.initModality(Modality.APPLICATION_MODAL); // Specifies the modality for this stage.
			tooLong.initOwner(mainStage); // Specifies the owner Window for this stage, or null for a top-level, unowned stage.
            VBox tooLongBox = new VBox(); // Creates a VBox called tooLongBox
            AnchorPane tooLongAnchor = new AnchorPane(); // Creates an AnchorPane called tooLongAnchor
            Button okButton = new Button("OK"); // Creates a button called okButton
            okButton.setLayoutX(150); // Sets the x location of okButton to 150
            okButton.setLayoutY(25); // Sets the y location of okButton to 25
            Label oklbl = new Label("Page has been cleared"); // Label that says Page has been cleared
            oklbl.setLayoutX(4); // Sets the x location of oklbl to 4
            oklbl.setLayoutY(5); // Sets the y location of oklbl to 5
            tooLongAnchor.getChildren().addAll(okButton,oklbl); // Adds everything to tooLongAnchor
            tooLongBox.getChildren().add(tooLongAnchor); // Adds everything to tooLongBox
            Scene dialogScene = new Scene(tooLongBox, 326, 68); // Creates a scene called dialogScene
            tooLong.setScene(dialogScene); // Puts the dialogScene scene into tooLong stage
            tooLong.show(); // Shows the tooLong stage
            tooLong.setResizable(false); // Makes sure that tooLong stage can't be resized
            okButton.setOnAction(e1 -> { // Event that closes tooLong stage
            	tooLong.close(); // Closes tooLong stage
            }); // okButton event ends
		} // End of try block
		catch (SQLException e1) { // Catch block, empty because there is no way of an exception happening
		} // End of catch block
	} // End of method
	
	/** Method that closes Pokédex Pro 
	 * @throws SQLException */
	public void closeMain() throws SQLException {
		mainStage.close(); // Closes Pokedex Pro
		connection.close(); // Closes database connection
	} // End of method
} // End of Main class