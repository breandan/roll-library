/* Copyright (c) 2016, 2017                                               */
/*       Institute of Software, Chinese Academy of Sciences               */
/* This file is part of ROLL, a Regular Omega Language Learning library.  */
/* ROLL is free software: you can redistribute it and/or modify           */
/* it under the terms of the GNU General Public License as published by   */
/* the Free Software Foundation, either version 3 of the License, or      */
/* (at your option) any later version.                                    */

/* This program is distributed in the hope that it will be useful,        */
/* but WITHOUT ANY WARRANTY; without even the implied warranty of         */
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the          */
/* GNU General Public License for more details.                           */

/* You should have received a copy of the GNU General Public License      */
/* along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

package roll.table;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import roll.words.Word;

/**
 * @author Yong Li (liyong@ios.ac.cn)
 */
// help to print the table out nicely
public class ObservationTablePrinterBoolean {

    private static int length(ExprValue exprValue) {
        if (exprValue instanceof ExprValueWord) {
            Word word = exprValue.get();
            return word.length();
        } else {
            Word left = exprValue.getLeft();
            Word right = exprValue.getRight();
            return left.length() + right.length() + 4;
        }
    }

    public static void print(ObservationTable table, OutputStream stream) {
        // PrintStream out = new PrintStream(stream);
        // List<ObservationRow> upperTable = table.getUpperTable();
        // List<ObservationRow> lowerTable = table.getLowerTable();
        // List<ExprValue> columns = table.getColumns();
        //
        // int[] maxNum4Cols = new int[columns.size() + 1];
        // for (int colNr = 0; colNr < maxNum4Cols.length; colNr++) {
        // maxNum4Cols[colNr] = 1;
        // }
        // for (ObservationRow row : upperTable) {
        // Word word = row.getWord();
        // maxNum4Cols[0] = Integer.max(word.length(), maxNum4Cols[0]);
        // }
        //
        // for (ObservationRow row : lowerTable) {
        // Word word = row.getWord();
        // maxNum4Cols[0] = Integer.max(word.length(), maxNum4Cols[0]);
        // }
        //
        // for (int colNr = 0; colNr < columns.size(); colNr++) {
        // ExprValue word = columns.get(colNr);
        // maxNum4Cols[colNr + 1] = Integer.max(word.toString().length(),
        // maxNum4Cols[colNr + 1]);
        // maxNum4Cols[colNr + 1] =
        // Integer.max(upperTable.get(0).getValues().get(colNr).toString().length(),
        // maxNum4Cols[colNr + 1]);
        // }
        // Formatter f = new Formatter(out);
        // // columns headers
        // f.format("%-" + maxNum4Cols[0] + "s || ", " ");
        // for (int colNr = 0; colNr < columns.size(); colNr++) {
        // f.format("%-" + maxNum4Cols[colNr + 1] + "s | ",
        // columns.get(colNr).toString());
        // }
        // f.format("\n");
        // // separator for headers
        // for (int colNr = 0; colNr < maxNum4Cols.length; colNr++) {
        // int numEq = maxNum4Cols[colNr] + 3;
        // for (int i = 0; i < numEq; i++) {
        // f.format("=");
        // }
        // }
        // f.format("\n");
        // // upper table
        // for (ObservationRow row : upperTable) {
        // f.format("%-" + maxNum4Cols[0] + "s || ",
        // row.getWord().toStringWithAlphabet());
        // for (int colNr = 0; colNr < columns.size(); colNr++) {
        // String str = row.getValues().get(colNr).toString();
        // f.format("%-" + maxNum4Cols[colNr + 1] + "s | ", str);
        // }
        // f.format("\n");
        // }
        // // separator for upper table
        // for (int colNr = 0; colNr < maxNum4Cols.length; colNr++) {
        // int numEq = maxNum4Cols[colNr] + 3;
        // for (int i = 0; i < numEq; i++) {
        // f.format("=");
        // }
        // }
        // f.format("\n");
        // // lower table
        // for (ObservationRow row : lowerTable) {
        // f.format("%-" + maxNum4Cols[0] + "s || ",
        // row.getWord().toStringWithAlphabet());
        // for (int colNr = 0; colNr < columns.size(); colNr++) {
        // String str = row.getValues().get(colNr).toString();
        // f.format("%-" + maxNum4Cols[colNr + 1] + "s | ", str);
        // }
        // f.format("\n");
        // }
        print(table, stream, false, new ArrayList<>());
    }

    public static void print(ObservationTable table, OutputStream stream, boolean nfa,
            List<ObservationRow> upperPrimeRows) {
        PrintStream out = new PrintStream(stream);
        List<ObservationRow> upperTable = table.getUpperTable();
        List<ObservationRow> lowerTable = table.getLowerTable();
        List<ExprValue> columns = table.getColumns();

        int[] maxNum4Cols = new int[columns.size() + 1];
        for (int colNr = 0; colNr < maxNum4Cols.length; colNr++) {
            maxNum4Cols[colNr] = 1;
        }
        for (ObservationRow row : upperTable) {
            Word word = row.getWord();
            // add two letters "* " in NFA table
            int num = word.length();
            if (nfa) {
                num += 2;
            }
            maxNum4Cols[0] = Integer.max(num, maxNum4Cols[0]);
        }

        for (ObservationRow row : lowerTable) {
            Word word = row.getWord();
            int num = word.length();
            if (nfa) {
                num += 2;
            }
            maxNum4Cols[0] = Integer.max(num, maxNum4Cols[0]);
        }

        for (int colNr = 0; colNr < columns.size(); colNr++) {
            ExprValue word = columns.get(colNr);
            maxNum4Cols[colNr + 1] = Integer.max(word.toString().length(), maxNum4Cols[colNr + 1]);
            maxNum4Cols[colNr + 1] = Integer.max(upperTable.get(0).getValues().get(colNr).toString().length(),
                    maxNum4Cols[colNr + 1]);
        }
        Formatter f = new Formatter(out);
        // columns headers
        f.format("%-" + maxNum4Cols[0] + "s || ", " ");
        for (int colNr = 0; colNr < columns.size(); colNr++) {
            f.format("%-" + maxNum4Cols[colNr + 1] + "s | ", columns.get(colNr).toString());
        }
        f.format("\n");
        // separator for headers
        for (int colNr = 0; colNr < maxNum4Cols.length; colNr++) {
            int numEq = maxNum4Cols[colNr] + 3;
            for (int i = 0; i < numEq; i++) {
                f.format("=");
            }
        }
        f.format("\n");
        // upper table
        for (ObservationRow row : upperTable) {
            String wordStr = "";
            if (nfa) {
                if(upperPrimeRows.contains(row)) {
                    wordStr = "* ";
                }else {
                    wordStr = "  ";
                }
                
            }
            wordStr += row.getWord().toStringWithAlphabet();
            f.format("%-" + maxNum4Cols[0] + "s || ", wordStr);
            for (int colNr = 0; colNr < columns.size(); colNr++) {
                String str = row.getValues().get(colNr).toString();
                f.format("%-" + maxNum4Cols[colNr + 1] + "s | ", str);
            }
            f.format("\n");
        }
        // separator for upper table
        for (int colNr = 0; colNr < maxNum4Cols.length; colNr++) {
            int numEq = maxNum4Cols[colNr] + 3;
            for (int i = 0; i < numEq; i++) {
                f.format("=");
            }
        }
        f.format("\n");
        // lower table
        for (ObservationRow row : lowerTable) {
            String wordStr = "";
            if (nfa) {
                boolean found = false;
                for (ObservationRow upperPrimeRow : upperPrimeRows) {
                    if (row.valuesEqual(upperPrimeRow)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    wordStr = "* ";
                }else {
                    wordStr = "  ";
                }
            }
            wordStr += row.getWord().toStringWithAlphabet();
            f.format("%-" + maxNum4Cols[0] + "s || ", wordStr);
            for (int colNr = 0; colNr < columns.size(); colNr++) {
                String str = row.getValues().get(colNr).toString();
                f.format("%-" + maxNum4Cols[colNr + 1] + "s | ", str);
            }
            f.format("\n");
        }
    }
}
