prop() {
  grep "^[[:space:]]*${1}" gradle.properties | cut -d'=' -f2 | sed 's/^[[:space:]]*//; s/\r//'
}

commit_tag=$(git log --pretty='%h' -1)
version=$(prop version)

# Set environment variable
echo "commit_tag=${commit_tag}" >> $GITHUB_ENV
echo "commit_msg=$(git log --pretty='> [%h] %s' -1)" >> $GITHUB_ENV
echo "branch_name=$(git branch --show-current)" >> $GITHUB_ENV
echo "version=${version}" >> $GITHUB_ENV

mkdir "target"
mv mint-server/build/libs/lemint-paperclip-$version-mojmap.jar target/LemonMint-$version.jar
