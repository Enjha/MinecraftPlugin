package fr.enjha.state;

public enum GameState {

    WAITING_FOR_START,
    IN_GAME,
    END_GAME;

    private GameState currentState;

    public void setCurrentGameState(GameState currentState){
        this.currentState = currentState;
    }

    public GameState getCurrentGameState(){
        return this.currentState;
    }

}
