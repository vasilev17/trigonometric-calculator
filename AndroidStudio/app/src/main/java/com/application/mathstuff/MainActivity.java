package com.application.mathstuff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mathstuff.R;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText input_sinNumerator;
    private TextInputEditText input_sinDenominator;
    private TextInputEditText input_cosNumerator;
    private TextInputEditText input_cosDenominator;
    private TextInputEditText input_tgNumerator;
    private TextInputEditText input_tgDenominator;
    private TextInputEditText input_cotgNumerator;
    private TextInputEditText input_cotgDenominator;

    private Button btn_calculate;
    private Button btn_clear;
    private Button btn_sinSqrt;
    private Button btn_cosSqrt;
    private Button btn_tgSqrt;
    private Button btn_cotgSqrt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupElements();

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_sinNumerator.setText("");
                input_sinDenominator.setText("");
                input_cosNumerator.setText("");
                input_cosDenominator.setText("");
                input_tgNumerator.setText("");
                input_tgDenominator.setText("");
                input_cotgNumerator.setText("");
                input_cotgDenominator.setText("");
            }
        });

        btn_sinSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input_sinNumerator.getEditableText().toString().contains("√")) {
                    input_sinNumerator.getEditableText().insert(0, "√");
                }
            }
        });

        btn_cosSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input_cosNumerator.getEditableText().toString().contains("√")) {
                    input_cosNumerator.getEditableText().insert(0, "√");
                }
            }
        });

        btn_tgSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input_tgNumerator.getEditableText().toString().contains("√")) {
                    input_tgNumerator.getEditableText().insert(0, "√");
                }
            }
        });

        btn_cotgSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input_cotgNumerator.getEditableText().toString().contains("√")) {
                    input_cotgNumerator.getEditableText().insert(0, "√");
                }
            }
        });
    }

    protected void setupElements() {
        input_sinNumerator = (TextInputEditText) findViewById(R.id.sinNumeratorInput);
        input_sinDenominator = (TextInputEditText) findViewById(R.id.sinDenominatorInput);
        input_cosNumerator = (TextInputEditText) findViewById(R.id.cosNumeratorInput);
        input_cosDenominator = (TextInputEditText) findViewById(R.id.cosDenominatorInput);
        input_tgNumerator = (TextInputEditText) findViewById(R.id.tgNumeratorInput);
        input_tgDenominator = (TextInputEditText) findViewById(R.id.tgDenominatorInput);
        input_cotgNumerator = (TextInputEditText) findViewById(R.id.cotgNumeratorInput);
        input_cotgDenominator = (TextInputEditText) findViewById(R.id.cotgDenominatorInput);

        btn_calculate = (Button) findViewById(R.id.calculateButton);
        btn_clear = (Button) findViewById(R.id.clearButton);
        btn_sinSqrt = (Button) findViewById(R.id.sqrtSinButton);
        btn_cosSqrt = (Button) findViewById(R.id.sqrtCosButton);
        btn_tgSqrt = (Button) findViewById(R.id.sqrtTgButton);
        btn_cotgSqrt = (Button) findViewById(R.id.sqrtCotgButton);
    }

    protected void clearAllInputFields() {
        btn_clear.performClick();
    }

    protected void compute() {
        if (input_sinNumerator.getText().toString().length() >= 1) {
            // sin -> others formula
            String sinNumerator = input_sinNumerator.getText().toString();
            String sinDenominator = input_sinDenominator.getText().toString();

            if (sinDenominator.isEmpty() || sinNumerator.equals("0")) {
                sinDenominator = "1";
            }

            Trigonometric sin = new Trigonometric(sinNumerator, sinDenominator);

            fromSin(sin);

        } else if (input_cosNumerator.getText().toString().length() >= 1) {

            // cos -> others formula
            String cosNumerator = input_cosNumerator.getText().toString();
            String cosDenominator = input_cosDenominator.getText().toString();

            if (cosDenominator.isEmpty() || cosNumerator.equals("0")) {
                cosDenominator = "1";
            }

            Trigonometric cos = new Trigonometric(cosNumerator, cosDenominator);

            fromCos(cos);

        } else if (input_tgNumerator.getText().toString().length() >= 1) {

            // tg -> others formula
            String tgNumerator = input_tgNumerator.getText().toString();
            String tgDenominator = input_tgDenominator.getText().toString();

            if (tgDenominator.isEmpty()) {
                tgDenominator = "1";
            }

            Trigonometric tg = new Trigonometric(tgNumerator, tgDenominator);

            fromTg(tg);

        } else if (input_cotgNumerator.getText().toString().length() >= 1) {

            // cotg -> others formula
            String cotgNumerator = input_cotgNumerator.getText().toString();
            String cotgDenominator = input_cotgDenominator.getText().toString();

            if (cotgDenominator.isEmpty()) {
                cotgDenominator = "1";
            }

            Trigonometric cotg = new Trigonometric(cotgNumerator, cotgDenominator);

            fromCotg(cotg);

        } else {

            // wrong input

        }
    }

    protected void fromSin(Trigonometric sin) {
        sinToCos(sin); // to Cos

        if (sin.m_numerator.equals("1") && sin.m_denominator.equals("1")) {
            input_tgNumerator.setText("none");
            input_tgDenominator.setText("none");
        } else {
            Trigonometric tg = sinToTg(sin); // to Tg
            input_tgNumerator.setText(tg.m_numerator);
            input_tgDenominator.setText(tg.m_denominator);
        }

        sinToCotg(sin);
    }

    protected void fromCos(Trigonometric cos) {
        Trigonometric sin = cosToSin(cos);

        Trigonometric tg = sinToTg(sin);
        input_tgNumerator.setText(tg.m_numerator);
        input_tgDenominator.setText(tg.m_denominator);

        sinToCotg(sin);
    }

    protected void fromTg(Trigonometric tg) {
        Trigonometric sin = tgToSin(tg); // Needed for formula reuse
        input_sinNumerator.setText(sin.m_numerator);
        input_sinDenominator.setText(sin.m_denominator);

        tgToCos(tg);

        tgToCotg(tg);
    }

    protected void fromCotg(Trigonometric cotg) {
        cotgToSin(cotg);

        cotgToCos(cotg);

        cotgToTg(cotg);
    }

    protected Trigonometric sinToCotg(Trigonometric sin) {
        Trigonometric reversedResult = sinToTg(sin);
        input_cotgNumerator.setText(reversedResult.m_denominator);
        input_cotgDenominator.setText(reversedResult.m_numerator);

        return reversedResult;
    }


    protected Trigonometric sinToTg(Trigonometric sin) {
        Trigonometric result = new Trigonometric();

        Trigonometric leftSide = sin;
        Trigonometric rightSide = sinToCos(sin);

        result.m_numerator = (leftSide.m_numerator + " * " + rightSide.m_denominator);
        result.m_denominator = (leftSide.m_denominator + " * " + rightSide.m_numerator);

        result.m_numeratorVal = (leftSide.m_numeratorVal / rightSide.m_denominatorVal);
        result.m_denominatorVal = (leftSide.m_denominatorVal / rightSide.m_numeratorVal);

        return result;
    }

    protected Trigonometric sinToCos(Trigonometric sin) {
        // 5/9
        // Setup
        String numerator = sin.m_numerator;
        double numeratorVal = 0.0;
        double numeratorValPowed = 0.0;

        String denominator = sin.m_denominator;
        double denominatorVal = 0.0;
        double denominatorValPowed = 0.0;

        Trigonometric result = new Trigonometric();
        double numeratorStepTwoVal = 0.0;

        // sin = koren(5)
        // sin = 2.33434532
        {
            // ~Step one
            // Final result:
            // 5/9 -> 25/81
            if (sin.m_numerator.contains("√")) {
                String sinNumeratorWhitoutSqrt = sin.m_numerator.substring(1);
                String squaredNumerator = "";
                double squaredNumeratorVal = 0;

                String squaredDenominator = "";

                squaredNumerator = sinNumeratorWhitoutSqrt;
                squaredDenominator = sin.m_denominator;

                try {
                    squaredNumeratorVal = Double.parseDouble(squaredNumerator);
                    numeratorVal = Math.sqrt(squaredNumeratorVal);
                    numeratorValPowed = Math.pow(numeratorVal, 2);
                    numeratorValPowed = Math.round(numeratorValPowed * 100) / 100.0d;
                    denominatorVal = Double.parseDouble(squaredDenominator);
                    denominatorValPowed = Math.pow(denominatorVal, 2);
                } catch (Exception e) {
                    clearAllInputFields();
                }

            } else {
                try {
                    numeratorVal = Double.parseDouble(numerator);
                    numeratorValPowed = Math.pow(numeratorVal, 2);
                } catch (Exception e) {
                    clearAllInputFields();
                }
                try {
                    denominatorVal = Double.parseDouble(denominator);
                    denominatorValPowed = Math.pow(denominatorVal, 2);

                } catch (Exception e) {
                    clearAllInputFields();
                }
            }
        }

        // Step two
        // Final result:
        // 1 - 25/81 -> 56/81
        numeratorStepTwoVal = denominatorValPowed - numeratorValPowed;

        // Step three
        // Final result:
        // 56/81 -> square(56/81)
        result.m_numerator = ("√" + numeratorStepTwoVal);
        result.m_denominator = ("" + Math.sqrt(denominatorValPowed));

        String setter = ("√" + numeratorStepTwoVal);
        input_cosNumerator.setText(setter);

        // setter = ( "√( " + denominatorValPowed + " )" );
        setter = (Math.sqrt(denominatorValPowed) + "");
        input_cosDenominator.setText(setter);

        return result;
    }

    protected Trigonometric cosToSin(Trigonometric cos) {
        Trigonometric result = sinToCos(cos); // sin to cos use the same formula as cos to sin

        input_sinNumerator.setText(result.m_numerator);
        input_sinDenominator.setText(result.m_denominator);

        return result;
    }

    protected Trigonometric tgToSin(Trigonometric n_tg) {
        Trigonometric tg = new Trigonometric();
        tg.m_numerator = n_tg.m_numerator;
        tg.m_denominator = n_tg.m_denominator;
        tg.m_numeratorVal = n_tg.m_numeratorVal;
        tg.m_denominatorVal = n_tg.m_denominatorVal;

        Trigonometric result = new Trigonometric();

        //(1)
        result.m_numerator = tg.m_numerator;
        result.m_denominator = f1(tg).m_numerator;

        return result;
    }

    protected Trigonometric tgToCotg(Trigonometric tg) {
        Trigonometric result = new Trigonometric();

        result.m_numerator = tg.m_denominator;
        result.m_denominator = tg.m_numerator;

        input_cotgNumerator.setText(result.m_numerator);
        input_cotgDenominator.setText(result.m_denominator);

        return result;
    }

    protected Trigonometric tgToCos(Trigonometric tg) {
        Trigonometric temp = f1(tg);

        Trigonometric result = new Trigonometric(temp.m_denominator, temp.m_numerator);

        input_cosNumerator.setText(result.m_numerator);
        input_cosDenominator.setText(result.m_denominator);

        return result;
    }

    protected Trigonometric cotgToSin(Trigonometric cotg) {
        Trigonometric sin = tgToCos(cotg);
        Trigonometric result = new Trigonometric(sin.m_numerator, sin.m_denominator);

        input_sinNumerator.setText(result.m_numerator);
        input_sinDenominator.setText(result.m_denominator);

        return result;
    }

    protected Trigonometric cotgToCos(Trigonometric cotg) {
        Trigonometric cos = tgToSin(cotg);
        Trigonometric result = new Trigonometric(cos.m_numerator, cos.m_denominator);

        input_cosNumerator.setText(result.m_numerator);
        input_cosDenominator.setText(result.m_denominator);

        return result;
    }

    protected Trigonometric cotgToTg(Trigonometric cotg) {
        Trigonometric result = new Trigonometric(cotg.m_denominator, cotg.m_numerator);

        input_tgNumerator.setText(result.m_numerator);
        input_tgDenominator.setText(result.m_denominator);

        return result;
    }

    protected Trigonometric f1(Trigonometric n_tg) {
        Trigonometric tg = new Trigonometric(n_tg.m_numerator, n_tg.m_denominator);
        Trigonometric result = new Trigonometric();

        if (tg.m_numerator.contains("√")) {
            tg.m_numerator = tg.m_numerator.substring(1);
            try {
                tg.m_numeratorVal = Double.parseDouble(tg.m_numerator);
            } catch (Exception e) {
                clearAllInputFields();
            }
        } else {
            try {
                tg.m_numeratorVal = Double.parseDouble(tg.m_numerator);
                tg.m_numeratorVal = Math.pow(tg.m_numeratorVal, 2);
            } catch (Exception e) {
                clearAllInputFields();
            }
        }
        try {
            tg.m_denominatorVal = Double.parseDouble(tg.m_denominator);
            result.m_numeratorVal = Math.pow(tg.m_denominatorVal, 2) + tg.m_numeratorVal;
            result.m_numerator = "√" + result.m_numeratorVal;
            result.m_numeratorVal = Math.sqrt(result.m_numeratorVal);
            result.m_denominator = tg.m_denominator;

        } catch (Exception e) {
            clearAllInputFields();
        }


        return result;
    }

}