/// <reference types="vitest" />
import {defineConfig} from 'vite';
import react from '@vitejs/plugin-react';
import tsconfigPaths from 'vite-tsconfig-paths';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react(), tsconfigPaths()],
  build: {
    outDir: 'build',
  },
  server: {
    port: 8000,
  },
  test: {
    globals: true,
  },
  define: {
    __API_HOST__: `"http://localhost:8080"`,
    __STRIPE_PUBLISHABLE_KEY__: `"pk_test_51OCRv0C7CYgP0DDWVpK4HplpoFYl8LR0b778BqK9XQUWMzA4KTA3yOMlK4iHSistVE308gz3SadUnUWiSRKYtTOK00R54nHVpA"`,
    __STRIPE_SECRET_KEY__: `"sk_test_51OCRv0C7CYgP0DDWr4UgBuOVlnVpUDkM8uLk60FRiwX2yUp9vOZEoHYrS3N1980z2Umt8UNV5bXOJqKQslEHPglu00Ke73HKjU"`,
  },
});
