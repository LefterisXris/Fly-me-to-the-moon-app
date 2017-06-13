package com.example.android.flymetothemoon;
// Γιώργος Στάθης
//465654165416541

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    /**
     * startSearchActivity called when START button is clicked
     * It opens the SearchActivity.
     */
    public void startSearchActivity(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    //TODO: (1) Προσθήκη στα settings επιλογή αλλαγής γλώσσας.
    //TODO: (2) Αλλαγή στον τρόπο με τον οποίο διαχειρίζεται η απάντηση της amadeus, όταν πρόκειται για Απλή Μετάβαση (δεν θα εμφανίζεται καθόλου η επιστροφή).
    //TODO: (3) Σμίκρυνση-αλλαγή εικόνας καθαρισμού πεδίων.
    //TODO: (4) Αποθήκευση δεδομένων κατά την επιστροφή σε parentActivity (από ShowResultsActivity σε SearchActivity με το βελάκι, να κρατάει τα δεδομένα).
    //TODO: (5) Validation control. Για να γίνει η αναζήτηση πρέπει να έχουν συμπληρωθεί τα απαραίτητα πεδία (από, προς, αναχώρηση, επιστροφή).
    //TODO: (6) Διόρθωση σφάλματος IATA api για μετάφραση αεροπορικών εταιριών, ή εύρεση άλλου τρόπου.
    //TODO: (7) Logos εταιριών.
    //TODO: (8) Οπτικοποίηση του βέλους για πτήσεις (border).
    //TODO: (9) Μορφοποίηση τιμών με χρήση xcliff.
    //TODO: (10) Προσθήκη fragment με άλλο api της amadeus π.χ. πτήσεις για οπουδήποτε.
    //TODO: (11) Παραπομπή στην σελίδα της εταιρίας με την πτήση.
    //TODO: (12) Οπτικοποίηση για χρήση στα πλάγια.
    //TODO: (13) Οπτικοποίηση για χρήση από tablet.
    //TODO: (14) Αναφορά σφάλματος.
    //TODO: (15) Credits.
    //TODO: (16) Ανέβασμα Google Play.

}
