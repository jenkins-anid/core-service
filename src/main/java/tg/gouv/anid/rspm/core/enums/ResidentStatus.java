package tg.gouv.anid.rspm.core.enums;

public enum ResidentStatus {
    CREATE, //Créer sans jamais appartenir à un ménage
    IN_HOUSEHOLD, //Dans un ménage actuellement
    DEPARTURE, //Déclaré pour quitter son ménage actuel
    BANNED, //Bannie dans le système pour fraude
    PENDING //En attente de validation par son nouveau chef de ménage
}
