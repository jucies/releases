#/bin/sh
set -e

openssl aes-256-cbc -K $encrypted_8cafb51ccccf_key -iv $encrypted_8cafb51ccccf_iv -in uc.key.enc -out uc.key -d

./gradlew --info generate publishGhPages
