using UnityEngine;

public class PlayerController : MonoBehaviour
{
    private Rigidbody2D Rigidbody2D;
    private float Horizontal;
    private float Vertical;
    private float Speed = 5f;

    public int vida = 3;
    public BarraVida barraVida; // Referencia a la barra de vida
    public GameObject gameOverCanvas; // Referencia al Canvas de Game Over

    void Start()
    {
        Rigidbody2D = GetComponent<Rigidbody2D>();
        barraVida.SetMaxHealth(vida);
        gameOverCanvas.SetActive(false); // Asegurarse de que el Canvas de Game Over esté desactivado al inicio
    }

    void Update()
    {
        Horizontal = Input.GetAxisRaw("Horizontal");
        Vertical = Input.GetAxisRaw("Vertical");
    }

    private void FixedUpdate()
    {
        Rigidbody2D.linearVelocity = new Vector2(Horizontal * Speed, Vertical * Speed);
    }

    public void RecibirDano(int cantidad)
    {
        vida -= cantidad;
        if (vida < 0) vida = 0;
        barraVida.ActualizarVida(vida);
        //Debug.Log("Vida restante: " + vida);

        if (vida <= 0)
        {
            Muerte();
        }
    }

    private void Muerte()
    {
        // Mostrar el Canvas de Game Over cuando el jugador muere
        gameOverCanvas.SetActive(true);
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.CompareTag("Enemy")) // Verifica si el objeto tiene el tag "Enemy"
        {
            RecibirDano(1); // Resta 1 de vida al jugador
        }
        else if (collision.CompareTag("Stalker"))
        {
            RecibirDano(vida);
        }
    }
}