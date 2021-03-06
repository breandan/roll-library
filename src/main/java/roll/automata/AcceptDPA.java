/* Copyright (c) 2018 -                                                   */
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

package roll.automata;

import gnu.trove.map.TIntIntMap;
import gnu.trove.map.hash.TIntIntHashMap;
import roll.automata.operations.DFAOperations;
import roll.util.UtilHelper;
import roll.util.sets.ISet;
import roll.words.Word;

/**
 * @author Yong Li (liyong@ios.ac.cn)
 * */

public class AcceptDPA implements Accept {

    private final DPA dpa;
    private TIntIntMap colors;
    
    public AcceptDPA(DPA dpa) {
        this.dpa = dpa;
        this.colors = new TIntIntHashMap();
    }
    
    @Override
    public boolean accept(ISet states) {
        int minColor = Integer.MAX_VALUE;
        for(final int state : states) {
            int color = colors.get(state);
            minColor = Integer.min(minColor, color);
        }
        if(UtilHelper.isEven(minColor)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean accept(Word prefix, Word period) {
        ISet infs = DFAOperations.getInfSet(dpa, prefix, period);
        return accept(infs);
    }
    
    public void setColor(int state, int color) {
        assert this.dpa.checkValidState(state);
        this.colors.put(state, color);
    }
    
    public int getColor(int state) {
        assert this.dpa.checkValidState(state);
        return this.colors.get(state);
    }

}
