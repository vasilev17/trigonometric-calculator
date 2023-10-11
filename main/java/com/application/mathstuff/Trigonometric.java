package com.application.mathstuff;

public class Trigonometric {
        public String m_numerator;
        public String m_denominator;

        public double m_numeratorVal;
        public double m_denominatorVal;

        public Trigonometric() {
           m_numerator = "";
            m_denominator = "";
            m_numeratorVal = 0;
            m_denominatorVal = 0;
        }

        public Trigonometric(String numerator, String denominator) {
             m_numerator = numerator;
             m_denominator = denominator;

            if (m_numerator.contains("√")) {
                String numeratorSqrt = m_numerator.substring(1);
                m_numeratorVal = Double.parseDouble(numeratorSqrt);
                m_numeratorVal = Math.sqrt(m_numeratorVal);
            }
            if(m_denominator.contains("√")) {
                String denominatorSqrt = m_denominator.substring(1);
                m_denominatorVal = Double.parseDouble(denominatorSqrt);
                m_denominatorVal = Math.sqrt(m_denominatorVal);
            }
        }

}
