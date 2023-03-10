package pl.it.portfolio.services;

import pl.it.portfolio.services.interfaces.IListingService;

import java.util.ArrayList;
import java.util.List;

public class ListingService implements IListingService {
    @Override
    public String createQuery(String name, String category, String delivery, String state, String localization, Double prize) {
        StringBuilder query = new StringBuilder("FROM pl.it.portfolio.model WHERE");

        ArrayList<String> params = new ArrayList<>(List.of(category, delivery, state, localization, prize.toString()));
        for (String param : params) {
            if (!param.equals("null")){
                query.append("")
                        //TODO dokończyć query



        }

    }
        return null;
}

}
