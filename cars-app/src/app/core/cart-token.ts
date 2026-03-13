// // src/app/core/cart-token.ts
// export function getCartToken(): string {
//   const key = 'cart_token';
//   let t = localStorage.getItem(key);
//   if (!t) { t = crypto.randomUUID(); localStorage.setItem(key, t); }
//   return t;
// }
export function getCartToken(): string {
  const key = 'cart_token';
  let t = localStorage.getItem(key);

  if (!t) {

    // Verificar correctamente si randomUUID existe
    if (typeof crypto !== 'undefined' && typeof crypto.randomUUID === 'function') {
      t = crypto.randomUUID();
    } else {
      // Generador alternativo compatible
      t = 'cart_' + Math.random().toString(36).substring(2) + Date.now().toString(36);
    }

    localStorage.setItem(key, t);
  }

  return t;
}

