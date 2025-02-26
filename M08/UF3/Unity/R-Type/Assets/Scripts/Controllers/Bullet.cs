using UnityEngine;
using TMPro;

public class Bullets : MonoBehaviour
{
    public ParticleSystem hitEffect;  // El prefab del sistema de partículas
    private BulletPool bulletPool;

    public void SetPool(BulletPool pool)
    {
        bulletPool = pool;
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.CompareTag("Enemy"))  
        {
            // Instanciamos el sistema de partículas en la posición del enemigo
            if (hitEffect != null) {
                ParticleSystem effect = Instantiate(hitEffect, transform.position, Quaternion.identity);
                effect.Play();
            }
            
            // Desactiva la bala
            gameObject.SetActive(false);
        }
    }
}