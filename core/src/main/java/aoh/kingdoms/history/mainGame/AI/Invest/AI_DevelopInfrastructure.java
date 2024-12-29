// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.AI.Invest;

import aoc.kingdoms.lukasz.map.province.Province;
import java.util.List;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;

public class AI_DevelopInfrastructure
{
    public static final void developInfrastructure(final int iCivID, final float minGold) {
        final int rand = Game.oR.nextInt(100);
        if (rand < Game.aiValuesBuild.DEVELOP_INFRASTRUCTURE_SCORE_RANDOM) {
            for (int i = Math.min(Game.aiValues.INVEST_LIMIT_PER_TURN_RANDOM_LIMIT, 1 + Game.getCiv(iCivID).getNumOfProvinces() / 2); i >= 0; --i) {
                final int randomProvince = Game.aiManager.getRandomProvince(iCivID);
                if (!Game.getProvince(randomProvince).addDevelopInfrastructure()) {
                    return;
                }
                if (minGold >= Game.getCiv(iCivID).fGold) {
                    return;
                }
            }
            return;
        }
        if (rand < Game.aiValuesBuild.DEVELOP_INFRASTRUCTURE_SCORE_GROWTH_RATE) {
            buildScore_InvestInEconomy_GrowthRate(iCivID);
        }
        else if (rand < Game.aiValuesBuild.DEVELOP_INFRASTRUCTURE_SCORE_DISTANCE) {
            buildScore_InvestInEconomy_Distance(iCivID);
        }
        else {
            buildScore_Economy(iCivID);
        }
        final List<Integer> tInvested = new ArrayList<Integer>();
        for (int j = Game.aiValues.INVEST_LIMIT_PER_TURN; j > 0; --j) {
            int investInProvinceID = -1;
            int checkInvestID;
            for (checkInvestID = 0; checkInvestID < Game.getCiv(iCivID).getNumOfProvinces(); ++checkInvestID) {
                if (!tInvested.contains(Game.getCiv(iCivID).getProvinceID(checkInvestID)) && Game.getProvince(Game.getCiv(iCivID).getProvinceID(checkInvestID)).aiInvestScore > 0.0f) {
                    investInProvinceID = checkInvestID;
                    break;
                }
            }
            if (investInProvinceID < 0) {
                tInvested.clear();
                return;
            }
            while (checkInvestID < Game.getCiv(iCivID).getNumOfProvinces()) {
                if (Game.getProvince(Game.getCiv(iCivID).getProvinceID(checkInvestID)).aiInvestScore > 0.0f && Game.getProvince(Game.getCiv(iCivID).getProvinceID(investInProvinceID)).aiInvestScore < Game.getProvince(Game.getCiv(iCivID).getProvinceID(checkInvestID)).aiInvestScore && !tInvested.contains(Game.getCiv(iCivID).getProvinceID(checkInvestID))) {
                    investInProvinceID = checkInvestID;
                }
                ++checkInvestID;
            }
            for (int a = 1 + Game.oR.nextInt(Game.aiValuesBuild.DEVELOP_INFRASTRUCTURE_RANDOM); a >= 0; --a) {
                if (!Game.getProvince(Game.getCiv(iCivID).getProvinceID(investInProvinceID)).addDevelopInfrastructure()) {
                    if (Game.getCiv(iCivID).fGold < Game.getDevelopInfrastructureCost(Game.getCiv(iCivID).getProvinceID(investInProvinceID))) {
                        tInvested.clear();
                        return;
                    }
                    if (Game.getCiv(iCivID).fLegacy < Game.getDevelopInfrastructureCostLegacy(Game.getCiv(iCivID).getProvinceID(investInProvinceID))) {
                        tInvested.clear();
                        return;
                    }
                }
            }
            if (minGold >= Game.getCiv(iCivID).fGold) {
                tInvested.clear();
                return;
            }
            tInvested.add(Game.getCiv(iCivID).getProvinceID(investInProvinceID));
            if (tInvested.size() == Game.getCiv(iCivID).getNumOfProvinces()) {
                tInvested.clear();
                return;
            }
        }
    }
    
    public static final void buildScore_InvestInEconomy_GrowthRate(final int iCivID) {
        for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
            buildScore_InvestInEconomy_GrowthRate_ProvinceID(Game.getCiv(iCivID).getProvinceID(i));
        }
    }
    
    public static final void buildScore_InvestInEconomy_GrowthRate_ProvinceID(final int provinceID) {
        if (Game.getProvince(provinceID).getInfrastructure() >= Game.getProvince(provinceID).iInfrastructureMax) {
            Game.getProvince(provinceID).aiInvestScore = 0.0f;
        }
        else {
            Game.getProvince(provinceID).aiInvestScore = Game.aiValues.SCORE_BASE + Game.aiValues.SCORE_INVEST_ECONOMY_GROWTH_RATE * (Game.getProvince(provinceID).getGrowthRateWithBonuses() / Game.getProvince(provinceID).getEconomyWithBonuses());
            final Province province = Game.getProvince(provinceID);
            province.aiInvestScore *= AI_InvestInEconomy.buildScore_CoreReligion(provinceID);
        }
    }
    
    public static final void buildScore_InvestInEconomy_Distance(final int iCivID) {
        for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
            buildScore_InvestInEconomy_Distance_ProvinceID(Game.getCiv(iCivID).getProvinceID(i));
        }
    }
    
    public static final void buildScore_InvestInEconomy_Distance_ProvinceID(final int provinceID) {
        if (Game.getProvince(provinceID).getInfrastructure() >= Game.getProvince(provinceID).iInfrastructureMax) {
            Game.getProvince(provinceID).aiInvestScore = 0.0f;
        }
        else {
            Game.getProvince(provinceID).aiInvestScore = Game.aiValues.SCORE_BASE + Game.aiValues.SCORE_INVEST_ECONOMY_DISTANCE * (Game.aiValues.SCORE_INVEST_ECONOMY_DISTANCE_W_GR * (Game.getProvince(provinceID).getGrowthRateWithBonuses() / Game.getProvince(provinceID).getEconomyWithBonuses()) * (Game.aiValues.SCORE_INVEST_ECONOMY_DISTANCE_W_DIS * (1.0f - Game.getProvince(provinceID).aiDistanceToCapital)));
            final Province province = Game.getProvince(provinceID);
            province.aiInvestScore *= AI_InvestInEconomy.buildScore_CoreReligion(provinceID);
        }
    }
    
    public static final void buildScore_Economy(final int iCivID) {
        for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
            buildScore_Economy_ProvinceID(Game.getCiv(iCivID).getProvinceID(i));
        }
    }
    
    public static final void buildScore_Economy_ProvinceID(final int provinceID) {
        if (Game.getProvince(provinceID).getInfrastructure() >= Game.getProvince(provinceID).iInfrastructureMax) {
            Game.getProvince(provinceID).aiInvestScore = 0.0f;
        }
        else {
            Game.getProvince(provinceID).aiInvestScore = Game.aiValues.SCORE_BASE + Game.aiValues.SCORE_DEVELOP_INFRASTRUCTURE_ECONOMY * Game.getProvince(provinceID).getEconomyWithBonuses() * (0.25f + 0.75f * (1.0f - Game.getProvince(provinceID).getInfrastructure() / (float)Game.getProvince(provinceID).iInfrastructureMax));
            final Province province = Game.getProvince(provinceID);
            province.aiInvestScore *= AI_InvestInEconomy.buildScore_CoreReligion(provinceID);
        }
    }
}
