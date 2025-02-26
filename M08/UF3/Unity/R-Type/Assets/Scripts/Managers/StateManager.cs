using UnityEngine;
using Enums;
public class StateManager : MonoBehaviour
{
    // Estado actual del juego (definido en el enum GameState)
    public GameState currentState;

    // Delegado y evento para notificar cuando el estado del juego ha cambiado
    public delegate void StateChangedHandler(GameState newState, float nextChangeAt = Mathf.Infinity);
    public event StateChangedHandler OnStateChanged;

    // Delegado y evento para notificar que los enemigos deben ser generados
    public delegate void StateHandler();
    public event StateHandler OnSpawn;


    private void Start()
    {
        SetState (GameState.start);    
    }

    /// <summary>
    /// Cambia el estado del juego y ejecuta las acciones correspondientes
    /// a cada estado. Dispara los eventos necesarios en cada transición.
    /// </summary>
    /// <param name="newState">Nuevo estado que se establecerá.</param>
    public void SetState(GameState newState)
    {
        currentState = newState;

        // Ejecutamos las acciones correspondientes a cada estado
        switch (newState)
        {
            case GameState.start:
                // Dispara el evento de cambio de estado
                OnStateChanged?.Invoke(newState, 2);
                // Inicia el intro después de un tiempo determinado
                Invoke("GoIntro", 2);
                break;

            case GameState.intro:
                // Dispara el evento de cambio de estado
                OnStateChanged?.Invoke(newState, 3);
                // Inicia la generación de enemigos después de un tiempo determinado
                Invoke("GoSpawn", 3);
                break;

            case GameState.spawnEnemies:
                // Dispara el evento de cambio de estado
                OnStateChanged?.Invoke(newState, 2);
                // Llama al evento para generar enemigos
                OnSpawn?.Invoke();
                // Inicia la fase de combate después de un tiempo determinado
                Invoke("GoCombat", 2);
                break;

            case GameState.win:
                // Dispara el evento de cambio de estado
                OnStateChanged?.Invoke(newState, 1);
                // Vuelve al intro después de un tiempo determinado (por ejemplo, al ganar el juego)
                Invoke("GoIntro", 1);
                break;

            default:
                // En cualquier otro caso, solo dispara el evento sin un tiempo específico
                OnStateChanged?.Invoke(newState, 0);
                break;
        }
    }

    /// <summary>
    /// Transición al estado "intro".
    /// Este método se llama después de que termine el tiempo de inicio.
    /// </summary>
    private void GoIntro()
    {
        SetState(GameState.intro);
    }

    /// <summary>
    /// Transición al estado "spawnEnemies".
    /// Este método se llama después de que termine el tiempo de intro.
    /// </summary>
    private void GoSpawn()
    {
        SetState(GameState.spawnEnemies);
    }

    /// <summary>
    /// Transición al estado "combat".
    /// Este método se llama después de que termine el tiempo de generación de enemigos.
    /// </summary>
    private void GoCombat()
    {
        SetState(GameState.combat);
    }

    /// <summary>
    /// Cambia el estado del juego a "gameOver".
    /// Este método se llama cuando el jugador pierde.
    /// </summary>
    public void SetGameOver()
    {
        SetState(GameState.gameOver);
    }
}