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

package roll.learner.fdfa;

import roll.automata.DFA;
import roll.learner.Learner;
import roll.table.HashableValue;
import roll.words.Word;

/**
 * @author Yong Li (liyong@ios.ac.cn)
 * */

public interface LearnerGeneral extends Learner<DFA, HashableValue> {

    default Word getStateLabel(Word word) {
        DFA dfa = this.getHypothesis();
        return getStateLabel(dfa.getSuccessor(word));
    }
    
    Word getStateLabel(int state);

}
