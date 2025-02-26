namespace Enums
{
    /// <summary>
    /// Define los diferentes estados del juego.
    /// </summary>
    public enum GameState
    {
        stoped,        // El juego está detenido
        start,         // Inicio del juego
        intro,         // Introducción antes de la acción
        spawnEnemies,  // Fase de generación de enemigos
        combat,        // Fase de combate en curso
        gameOver,      // El jugador ha perdido
        pause,         // Juego en pausa
        win            // El jugador ha ganado
    }

    public enum FxSound
    {
        hit,
        Explosion,
        Click,
        PowerUp,
        Win,
        Died
    }
    public enum MusicTrack
    {
        MainTheme,
        BattleTheme,
        VictoryTheme,
        GameOverTheme,
        IntroTheme
    }
}