// copying imports/packages from DetectLanguage.java
package com.google.refine.expr.functions.strings;

import java.util.Properties;

import com.google.refine.clustering.knn.SimilarityDistance;
import com.google.refine.clustering.knn.VicinoDistance;
import com.google.refine.expr.EvalError;
import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.EvalErrorMessage;
import com.google.refine.grel.Function;
import com.google.refine.grel.FunctionDescription;

// Commenting out import below which may not be relevant
// import com.google.refine.util.DetectLanguageUtils;

public class ToEnglish implements Function {
    /**
     * Read the column that contains the strings to be translated and store in a variable.
     * For each row in that column, detect the language
     * From the detected language, translate to English (How?)
     * Return the translated column as a new column
    */

    // Need to fix this call method
    @Override
    public Object call(Properties bindings, Object[] args) {
        if (args != null && args.length == 2 && args[0] instanceof String && args[1] instanceof String) {
            String s1 = (String) args[0];
            String s2 = (String) args[1];

            SimilarityDistance levenshteinDistance = new VicinoDistance(new edu.mit.simile.vicino.distances.LevenshteinDistance());
            return levenshteinDistance.compute(s1, s2);
        }
        return new EvalError(EvalErrorMessage.expects_two_strings(ControlFunctionRegistry.getFunctionName(this)));
     }

    @Override
    public String getDescription() {
        return FunctionDescription.str_to_english();
    }

    @Override
    public String getParams() {
        return "string s";
    }

    @Override
    public String getReturns() {
        return "string";
    }
}