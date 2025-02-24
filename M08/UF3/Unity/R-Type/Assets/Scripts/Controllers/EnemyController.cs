using UnityEngine;
using System.Collections;

public class EnemyController : MonoBehaviour
{
    public float speed = 2f;
    public float distance = 3f;
    private float startY;
    public ParticleSystem hitEffect;
    int lives = 3;

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        startY = transform.position.y;
        hitEffect.Stop();
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
            lives--;
            if(lives == 0 && hitEffect != null) {
                hitEffect.Play();
                StartCoroutine(RespawnEnemy());
            }
        }
    }

    private IEnumerator RespawnEnemy() {
        GetComponent<SpriteRenderer>().enabled = false;
        GetComponent<Collider2D>().enabled = false;

        yield return new WaitForSeconds(3f);
        
        lives = 3;
        gameObject.SetActive(true);
        transform.position = new Vector3(transform.position.x, startY, transform.position.z);

        GetComponent<SpriteRenderer>().enabled = true;
        GetComponent<Collider2D>().enabled = true;
    }
}