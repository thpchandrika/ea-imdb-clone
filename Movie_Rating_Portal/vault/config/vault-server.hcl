disable_mlock = true
ui            = true

listener "tcp" {
  address     = "127.0.0.1:8200"
  tls_disable = "true"
}
backend "file" {
  path     = "/vault/file"
}
