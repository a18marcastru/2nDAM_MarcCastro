using UnityEngine;

public class EnemyManager : MonoBehaviour
{
    // Prefabricado del enemigo que se generará en cada ola de enemigos
    public GameObject enemyPrefab;

    // Número actual de enemigos en la pantalla
    public int currentEnemies;

    public StateManager stateManager;
    // Número de la siguiente ola de enemigos
    private int nextWave = 3;

    // Start es llamado antes de la primera actualización
    private void Start()
    {
        // Nos suscribimos al evento OnSpawn para generar una nueva ola de enemigos
        stateManager.OnSpawn += SpawnWave;
    }

    /// <summary>
    /// Genera una nueva ola de enemigos y los coloca en la pantalla.
    /// La cantidad de enemigos en la ola depende de la variable nextWave.
    /// </summary>
    public void SpawnWave()
    {
        // Generamos enemigos en la cantidad de la siguiente ola
        for (int i = 0; i < nextWave; i++)
        {
            // Generamos una posición aleatoria en el eje Y para cada enemigo
            float randomY = Random.Range(-4.5f, 4.5f);

            Instantiate(enemyPrefab, new Vector3(8, randomY, 0), Quaternion.identity);
        }

        // Actualizamos el número actual de enemigos con la cantidad de la ola generada
        currentEnemies = nextWave;
        // Incrementamos la dificultad de la siguiente ola con un valor
        // aleatorio entre 2 y 3 enemigos ... revisar si es necessita un 4
        nextWave += Random.Range(2, 3);

        //limitamos el maximo
        nextWave = (nextWave > 8)? 8 : nextWave;  //resultat = (condició)? true : false;
    }

    /// <summary>
    /// Disminuye el número de enemigos activos en la pantalla.
    /// Si no hay más enemigos, se muestra el mensaje "You win!" y se cambia el estado del juego.
    /// </summary>
    public void EnemyKilled()
    {
        // Reducimos la cantidad de enemigos actuales
        currentEnemies--;

        // Si ya no quedan enemigos, el jugador ha ganado
        if (currentEnemies <= 0)
        {
            Debug.Log("You win!");
            // Cambiamos el estado del juego a 'intro'
            // (esto podría ser el inicio de un nuevo nivel o pantalla)
            stateManager.SetState(Enums.GameState.intro);
        }
    }

    // OnDestroy se llama cuando el objeto es destruido
    private void OnDestroy()
    {
        // Nos desuscribimos del evento OnSpawn cuando el objeto es
        // destruido para evitar errores
        stateManager.OnSpawn -= SpawnWave;
    }
}