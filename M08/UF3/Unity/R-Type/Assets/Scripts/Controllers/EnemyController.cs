using UnityEngine;
using System.Collections;

public class EnemyController : MonoBehaviour
{
    public float speed = 2f;         // Velocidad del movimiento
    public float distance = 3f;      // Distancia que sube y baja
    private float startY;            // Posición inicial en Y
    int lives = 3;

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        startY = transform.position.y;
    }

    // Update is called once per frame
    void Update()
    {
        float newY = startY + Mathf.PingPong(Time.time * speed, distance * 2) - distance;
        transform.position = new Vector3(transform.position.x, newY, transform.position.z);
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.CompareTag("Bullet"))  
        {
            Debug.Log("Dado");
            lives--;
            if(lives == 0) {
                StartCoroutine(RespawnEnemy()); 
                gameObject.SetActive(false);
            }
        }
    }

    private IEnumerator RespawnEnemy() {
        yield return new WaitForSeconds(3f);
        lives = 3;
        gameObject.SetActive(true);
        transform.position = new Vector3(0, 0, 1);
    }
}