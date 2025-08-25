prop() {
  grep "^[[:space:]]*${1}" gradle.properties | cut -d'=' -f2 | sed 's/^[[:space:]]*//; s/\r//'
}

commit_tag=$(git log --pretty='%h' -1)
version=$(prop version)
mcVersion=$(prop mcVersion)

# Set environment variable
echo "commit_tag=${commit_tag}" >> $GITHUB_ENV
echo "commit_msg=$(git log --pretty='> [%h] %s' -1)" >> $GITHUB_ENV
echo "branch_name=$(git branch --show-current)" >> $GITHUB_ENV
echo "version=${version}" >> $GITHUB_ENV
echo "mcversion=$(prop mcVersion)" >> $GITHUB_ENV

mkdir "target"
mv lemonmint-server/build/libs/lemonmint-paperclip-$version-mojmap.jar target/LemonMint-$mcVersion-$commit_tag.jar
