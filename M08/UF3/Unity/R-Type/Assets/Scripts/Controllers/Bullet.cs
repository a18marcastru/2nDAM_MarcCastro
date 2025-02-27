using UnityEngine;
using TMPro;

public class Bullets : MonoBehaviour
{
    public ParticleSystem hitEffect;  // El prefab del sistema de partículas
    private PlayerController bulletPool;

    public void SetPool(PlayerController pool)
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
            bulletPool.ReturnBullet(gameObject);
        }
    }
}