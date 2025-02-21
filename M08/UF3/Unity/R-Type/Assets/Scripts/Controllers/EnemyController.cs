using UnityEngine;

public class EnemyController : MonoBehaviour
{
    public ParticleSystem hitEffect;
    int lives = 3;

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        hitEffect.Stop();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.CompareTag("Bullet"))  
        {
            Debug.Log("Dado");
            if(hitEffect != null) {
                hitEffect.Play();
                lives--;
                if(lives == 0) {
                    Destroy(gameObject);
                }
            }
        }
    }
}
