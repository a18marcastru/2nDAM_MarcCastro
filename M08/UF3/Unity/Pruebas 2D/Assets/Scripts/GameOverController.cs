using UnityEngine;
using UnityEngine.SceneManagement;  // Necesario para cargar escenas

public class GameOverController : MonoBehaviour
{
    public void ReiniciarNivel()
    {
        // Vuelve a cargar la escena actual
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
    }
}
